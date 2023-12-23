package fr.teama.generator;

import fr.teama.App;
import fr.teama.structural.interfaces.Note;
import fr.teama.structural.interfaces.Track;

import javax.sound.midi.*;
import java.util.List;

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
			List<Track> tracks = app.getTracks();

			if (!tracks.isEmpty()) {
				Track firstTrack = tracks.get(0);

				sequencer.setTempoInBPM(firstTrack.getMeasure().getTempo());

				sequence = new Sequence(Sequence.PPQ, firstTrack.getMeasure().getResolution());
				tracks.forEach(track -> track.accept(this));

				sequencer.setSequence(sequence);
				sequencer.start();

				while (sequencer.isRunning()) {
					Thread.sleep(10);
				}
				sequencer.stop();
				sequencer.close();
			}
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
	public void visit(Track track) {
		lastTick = 0;
		lastTrack = sequence.createTrack();
		currentInstrumentChannelNumber = track.getInstrument().getInstrumentChannelNumber();
		track.getMeasure().getNotes().forEach(note -> note.accept(this));
	}

	@Override
	public void visit(Note note) {
		try {
			// in case we want a silence
			if (note.getNoteDurationEnum() == null) {
				lastTick += note.getNoteDurationEnum().getDuration() + 1;
				return;
			}

			ShortMessage noteOn = new ShortMessage();
			noteOn.setMessage(ShortMessage.NOTE_ON, currentInstrumentChannelNumber, note.getNoteDurationEnum().getDuration(), 100);
			MidiEvent noteOnEvent = new MidiEvent(noteOn, lastTick);
			lastTrack.add(noteOnEvent);

			ShortMessage noteOff = new ShortMessage();
			noteOff.setMessage(ShortMessage.NOTE_OFF, currentInstrumentChannelNumber, note.getNoteDurationEnum().getDuration(), 100);
			MidiEvent noteOffEvent = new MidiEvent(noteOff, lastTick + note.getNoteDurationEnum().getDuration());
			lastTrack.add(noteOffEvent);

			lastTick += note.getNoteDurationEnum().getDuration() + 1;
		} catch (InvalidMidiDataException e) {
			throw new RuntimeException(e);
		}
	}

}
