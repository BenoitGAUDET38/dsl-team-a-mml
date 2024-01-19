package fr.teama.generator;

import fr.teama.App;
import fr.teama.behaviour.TempoEvent;
import fr.teama.exceptions.InconsistentBarException;
import fr.teama.exceptions.InvalidOctaveExtension;
import fr.teama.exceptions.NoRootNormalBarFoundException;
import fr.teama.structural.*;
import fr.teama.structural.Track;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;

public class ToSinging extends Visitor<StringBuffer> {
    Sequence sequence;
    javax.sound.midi.Track currentTrack;
    NormalBar currentBar;

    int currentBarTick=1;
    int currentChannelNumber;
    int currentClassicChannelNumber = 0;
    int currentDrumChannelNumber = 9;
    int currentInstrumentNumber = 0;
    int globalUnityTimeValue = 0;

    int currentResolution = 4;
    int currentUnityTimeValue = 4;
    int currentTempo = 120;
    int currentVolume = 60;
    int tickMultiplier;

    @Override
    public void visit(App app) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            List<Track> tracks = app.getTracks();

            if (tracks.isEmpty()) {
                throw new IllegalStateException("No track in the app");
            }

            if (tracks.get(0).getBars().isEmpty()) {
                throw new IllegalStateException("No bar in the first track");
            }

            sequencer.setTempoInBPM(currentTempo);

            // Resolution management
            Set<Integer> unityTimeValues= new HashSet<>();
            app.getTracks().forEach(track -> track.getBars().forEach(bar -> {
                if (bar instanceof NormalBar) {
                    unityTimeValues.add(((NormalBar) bar).getUnityTimeValue());
                }
            }));
            for (int r : unityTimeValues) {
                if (globalUnityTimeValue == 0) {
                    globalUnityTimeValue = r;
                } else {
                    globalUnityTimeValue *= r;
                }
            }

            sequence = new Sequence(Sequence.PPQ, globalUnityTimeValue);
            app.getTracks().forEach(track -> track.accept(this));
            Optional<String> appName = app.getName();
            String outputDirectory = "output-midi";

            // Create the output directory if it doesn't exist
            File outputDir = new File(outputDirectory);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            String filename = appName.isPresent() ? appName.get() + ".mid" : "default.mid";
            File midiFile = new File(outputDirectory, filename);
            MidiSystem.write(sequence, 1, midiFile);
            System.out.println("File generated successfully: \n"+midiFile.getAbsolutePath());
            sequencer.setSequence(sequence);
            sequencer.start();

            while (sequencer.isRunning()) {
                Thread.sleep(10);
            }

            sequencer.stop();
            sequencer.close();


        } catch (InvalidMidiDataException | MidiUnavailableException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void visit(Track track) {
        currentBarTick = 1;
        currentTrack = sequence.createTrack();
        currentInstrumentNumber = track.getInstrument().getInstrumentNumber();
        currentVolume = track.getVolume();

        if (currentInstrumentNumber != -1 && currentClassicChannelNumber > 15) {
            throw new IllegalStateException("Too many tracks with classic instruments (max 14)");
        }
        if (currentInstrumentNumber == -1 && currentDrumChannelNumber > 9) {
            throw new IllegalStateException("Too many tracks with drum instruments (max 1)");
        }

        if (currentInstrumentNumber != -1) {
            currentChannelNumber = currentClassicChannelNumber;
            ShortMessage instrumentChange = new ShortMessage();
            try {
                // .setMessage(ShortMessage.PROGRAM_CHANGE, channelToChange, instrumentNumber, idk so 0);
                instrumentChange.setMessage(ShortMessage.PROGRAM_CHANGE, currentChannelNumber, currentInstrumentNumber, 0);
                MidiEvent instrumentChangeEvent = new MidiEvent(instrumentChange, 0);
                currentTrack.add(instrumentChangeEvent);
            } catch (InvalidMidiDataException e) {
                throw new RuntimeException(e);
            }
        } else {
            currentChannelNumber = currentDrumChannelNumber;
        }

        System.out.println("Track " + track.getInstrument() + " on channel " + currentChannelNumber);

        track.getBars().forEach(bar -> {
            try {
                bar.accept(this);
            } catch (InconsistentBarException | CloneNotSupportedException | NoRootNormalBarFoundException e) {
                throw new RuntimeException(e);
            }
        });
        if (currentInstrumentNumber != -1) {
            currentClassicChannelNumber++;
            if (currentClassicChannelNumber == 9) {
                currentClassicChannelNumber = 10;
            }
        } else {
            currentDrumChannelNumber++;
        }
    }

    @Override
    public void visit(ReusedBar reusedBar) throws CloneNotSupportedException, NoRootNormalBarFoundException, InconsistentBarException {
        NormalBar normalBar = applyManipulations(reusedBar);
        for (int i = 0; i < reusedBar.getRepetition(); i++) {
            normalBar.accept(this);
        }
    }

    // Recursively apply manipulations to the reused bar
    private NormalBar applyManipulations(ReusedBar reusedBar) throws CloneNotSupportedException, NoRootNormalBarFoundException {
        if (reusedBar.getBar() instanceof NormalBar) {
            NormalBar normalBar = (NormalBar) ((NormalBar) reusedBar.getBar()).clone();
//            initializeBarNotesTick(normalBar.getNotes());
            normalBar.setNotes(initializeBarNotesTick(normalBar.getNotes()));
            reusedBar.getManipulations().forEach(manipulation -> manipulation.apply(normalBar));
            return normalBar;
        }

        if (reusedBar.getBar() == null) {
            throw new NoRootNormalBarFoundException("No bar to reuse");
        }

        NormalBar normalBar = applyManipulations((ReusedBar) reusedBar.getBar());
        reusedBar.getManipulations().forEach(manipulation -> manipulation.apply(normalBar));
        return normalBar;
    }

    private List<Note> initializeBarNotesTick(List<Note> notes){
        int tick = 0;
        for (Note note : notes) {
            if (note.getTick().isEmpty()) {
                note.setTick(Optional.of(tick));
                tick += note.getNoteDuration().getDuration()*Math.pow(2, currentUnityTimeValue/4 - 1);
            }
        }
        return notes;
    }

    @Override
    public void visit(NormalBar normalBar) throws InconsistentBarException {
        // Check if the tempo has changed
        if (normalBar.getTempo() != currentTempo) {
            currentTrack.add(TempoEvent.createTempoEvent(normalBar.getTempo(), currentBarTick));
            currentTempo = normalBar.getTempo();
        }

        // Check if the resolution has changed
        currentUnityTimeValue = normalBar.getUnityTimeValue();
        currentResolution = normalBar.getResolution();


        initializeBarNotesTick(normalBar.getNotes());
        verifyValidityOfBar(normalBar);
        currentBar = normalBar;
        tickMultiplier = globalUnityTimeValue / currentBar.getUnityTimeValue();
        normalBar.getNotes().forEach(note -> note.accept(this));
        currentBarTick += (normalBar.numberOfTicksInBar()) * tickMultiplier;
    }

    private void verifyValidityOfBar(NormalBar normalBar) throws InconsistentBarException {
        for (Note note : normalBar.getNotes()) {
            double currentTick= note.getTick().get();
            if (currentTick + note.getNoteDuration().getDuration() > normalBar.numberOfTicksInBar()) {
                throw new InconsistentBarException("Note duration is too long for the bar : " + normalBar);
            }

        }
    }

    @Override
    public void visit(Note note) {
        try {
            System.out.println(note);

            int tick;
            if (note.getNoteNumber().getNoteNumber() == -1) {
                return;
            }
            tick = currentBarTick + note.getTick().get() * tickMultiplier;


            // Apply octave to note number
            int noteNumber;
            if (note.getOctave() > 7 || note.getOctave() < -2 || note.getOctave() == 0) {
                throw new InvalidOctaveExtension("Invalid octave extension : " + note.getOctave());
            } else if (note.getOctave() > 0) {
                noteNumber = note.getNoteNumber().getNoteNumber() + (note.getOctave() - 3) * 12;
            } else {
                noteNumber = note.getNoteNumber().getNoteNumber() + (note.getOctave() - 2) * 12;
            }


            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, currentChannelNumber, noteNumber, currentVolume);
            MidiEvent noteOnEvent = new MidiEvent(noteOn, tick);
            currentTrack.add(noteOnEvent);

            tick += note.getNoteDuration().getDuration() * tickMultiplier;

            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, currentChannelNumber, noteNumber, currentVolume);
            MidiEvent noteOffEvent = new MidiEvent(noteOff, tick);
            currentTrack.add(noteOffEvent);
        } catch (InvalidMidiDataException | InvalidOctaveExtension e) {
            throw new RuntimeException(e);
        }
    }
}
