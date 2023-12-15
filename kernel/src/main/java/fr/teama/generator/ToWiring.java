package fr.teama.generator;

import fr.teama.App;
import fr.teama.structural.*;
import javax.sound.midi.*;

public class ToWiring extends Visitor<StringBuffer> {
	Sequence sequence;
	javax.sound.midi.Track lastTrack;
	int lastTick = 0;
	int currentInstrumentChannelNumber = 0;
	String outputFolderPath = "output-midi/";

	@Override
	public void visit(App app) {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setTempoInBPM(app.getTempo());

			sequence = new Sequence(Sequence.PPQ, 4);
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


//		try {
//			sequence = new Sequence(Sequence.PPQ, 4);
//			app.getTracks().forEach(track -> track.accept(this));
//			File midiFile = new File(outputFolderPath + app.getName() + ".mid");
//			MidiSystem.write(sequence, 1, midiFile);
//		} catch (InvalidMidiDataException | IOException e) {
//			System.out.println("Error while writing midi file");
//			throw new RuntimeException(e);
//		}
    }

	@Override
	public void visit(TrackPiano trackPiano) {
		lastTick = 0;
		lastTrack = sequence.createTrack();
		currentInstrumentChannelNumber = trackPiano.getInstrument().getInstrumentChannelNumber();
		trackPiano.getNotes().forEach(note -> note.accept(this));
	}

	@Override
	public void visit(TrackDrum trackDrum) {
		lastTick = 0;
		lastTrack = sequence.createTrack();
		currentInstrumentChannelNumber = trackDrum.getInstrument().getInstrumentChannelNumber();
		trackDrum.getDrumNotes().forEach(note -> note.accept(this));
	}

	@Override
	public void visit(Note note) {
		try {
			// in case we want a silence
			if (note.getNote() == null) {
				lastTick += note.getTick() + 1;
				return;
			}

			ShortMessage noteOn = new ShortMessage();
			noteOn.setMessage(ShortMessage.NOTE_ON, currentInstrumentChannelNumber, note.getNote().getNoteNumber(), 100);
			MidiEvent noteOnEvent = new MidiEvent(noteOn, lastTick);
			lastTrack.add(noteOnEvent);

			ShortMessage noteOff = new ShortMessage();
			noteOff.setMessage(ShortMessage.NOTE_OFF, currentInstrumentChannelNumber, note.getNote().getNoteNumber(), 100);
			MidiEvent noteOffEvent = new MidiEvent(noteOff, lastTick + note.getTick());
			lastTrack.add(noteOffEvent);

			lastTick += note.getTick() + 1;
		} catch (InvalidMidiDataException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void visit(NoteDrum noteDrum) {

	}
}
