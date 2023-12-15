package fr.teama;

import javax.sound.midi.*;
import java.io.File;

public class Example {
    public static void main(String[] args) {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();

            MidiChannel[] channels = synth.getChannels();
            Soundbank bank = synth.getDefaultSoundbank();
            System.out.println("Bank: " + bank.getResources().toString());
            synth.loadAllInstruments(bank);
            Instrument[] instruments = synth.getLoadedInstruments();
            System.out.println("Instruments: " + instruments.toString());
            Instrument flute = null;
            for (int i=0; i < instruments.length; i++)
            {
                if(instruments[i].getName().equals("Flute"))
                {
                    flute = instruments[i];
                }
            }
//            Patch flutePatch = flute.getPatch();

            // Obtenez une instance du séquenceur
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // Créez une nouvelle séquence
            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            // Obtenez une piste de la séquence
            Track track = sequence.createTrack();

            // Ajoutez quelques événements MIDI à la piste
//            for (int i = 0; i < 127; i++) {
//                ShortMessage noteOn = new ShortMessage();
//                noteOn.setMessage(ShortMessage.NOTE_ON, 0, i, 100);
//                MidiEvent noteOnEvent = new MidiEvent(noteOn, i);
//                track.add(noteOnEvent);
//
//                ShortMessage noteOff = new ShortMessage();
//                noteOff.setMessage(ShortMessage.NOTE_OFF, 0, i, 100);
//                MidiEvent noteOffEvent = new MidiEvent(noteOff, i+1);
//                track.add(noteOffEvent);
//            }

            // Enregistrez la séquence dans un fichier .mid
//            File midiFile = new File("output-midi/exemple.mid");
//            MidiSystem.write(sequence, 1, midiFile); // Utilisez 1 pour le format 1 (séquence multipiste)

            ShortMessage noteOn = new ShortMessage();
            noteOn.setMessage(ShortMessage.NOTE_ON, 0, 36, 100);
            MidiEvent noteOnEvent = new MidiEvent(noteOn, 0);
            track.add(noteOnEvent);

            ShortMessage noteOff = new ShortMessage();
            noteOff.setMessage(ShortMessage.NOTE_OFF, 0,36, 100);
            MidiEvent noteOffEvent = new MidiEvent(noteOff, 4);
            track.add(noteOffEvent);

            // Configurez le séquenceur avec la séquence créée
            sequencer.setSequence(sequence);

            // Démarrez la lecture
            sequencer.start();

            // Attendez que la séquence soit lue avant de fermer le séquenceur
            while (sequencer.isRunning()) {
                Thread.sleep(100);
            }

            // Arrêtez le séquenceur et fermez-le
            sequencer.stop();
            sequencer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
