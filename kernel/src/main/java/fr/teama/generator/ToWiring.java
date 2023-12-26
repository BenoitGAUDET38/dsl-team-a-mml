package fr.teama.generator;

import fr.teama.App;
import fr.teama.behaviour.TempoEvent;
import fr.teama.exceptions.InconsistentBarException;
import fr.teama.structural.Bar;
import fr.teama.structural.Note;
import fr.teama.structural.Track;

import javax.sound.midi.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class ToWiring extends Visitor<StringBuffer> {
    Sequence sequence;
    javax.sound.midi.Track currentTrack;
    Bar currentBar;
    int currentTick = 1;
    int currentInstrumentChannelNumber;
    int globalResolution = 0;
    int currentResolution = 4;
    int currentTempo = 120;

    @Override
    public void visit(App app) {
        try {
            System.out.println("STARTING GENERATION");
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
            Set<Integer> resolutions = new HashSet<>();
            app.getTracks().forEach(track -> track.getBars().forEach(bar -> resolutions.add(bar.getResolution())));
            for (int r : resolutions) {
                if (globalResolution == 0) {
                    globalResolution = r;
                } else {
                    globalResolution *= r;
                }
            }

            sequence = new Sequence(Sequence.PPQ, globalResolution);
            app.getTracks().forEach(track -> track.accept(this));

            sequencer.setSequence(sequence);
            sequencer.start();

            while (sequencer.isRunning()) {
                Thread.sleep(10);
            }

            sequencer.stop();
            sequencer.close();
        } catch (InvalidMidiDataException | MidiUnavailableException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void visit(Track track) {
        currentTick = 1;
        currentTrack = sequence.createTrack();
        currentInstrumentChannelNumber = track.getInstrument().getInstrumentChannelNumber();
        track.getBars().forEach(bar -> {
            try {
                bar.accept(this);
            } catch (InconsistentBarException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void visit(Bar bar) throws InconsistentBarException {
        // Check if the tempo has changed
        if (bar.getTempo() != currentTempo) {
            currentTrack.add(TempoEvent.createTempoEvent(bar.getTempo(), currentTick));
            currentTempo = bar.getTempo();
        }

        // Check if the resolution has changed
        if (bar.getResolution() != currentResolution) {
            currentResolution = bar.getResolution();
        }

        if (!checkBarTotalDuration(bar)) {
            throw new InconsistentBarException("Bar notes different from bar resolution : " + bar);
        }

        currentBar = bar;
        bar.getNotes().forEach(note -> note.accept(this));
    }

    @Override
    public void visit(Note note) {
        try {
            int tickMultiplier = globalResolution / currentBar.getResolution();

            // Manage silence
            if (note.getNoteNumber() == -1) {
                currentTick += (note.getNoteDurationEnum().getDuration() * tickMultiplier) + 1;
                return;
            }

            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, currentInstrumentChannelNumber, note.getNoteNumber(), 60);
            MidiEvent noteOnEvent = new MidiEvent(noteOn, currentTick);
            currentTrack.add(noteOnEvent);

            currentTick += note.getNoteDurationEnum().getDuration() * tickMultiplier;

            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, currentInstrumentChannelNumber, note.getNoteNumber(), 60);
            MidiEvent noteOffEvent = new MidiEvent(noteOff, currentTick + ((long) note.getNoteDurationEnum().getDuration() * tickMultiplier));
            currentTrack.add(noteOffEvent);

            currentTick += 1;
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkBarTotalDuration(Bar bar) {
        float totalDuration = 0;
        for (Note note : bar.getNotes()) {
            totalDuration += note.getNoteDurationEnum().getDuration();
        }
        return totalDuration / 4 == bar.getResolution();
    }

}
