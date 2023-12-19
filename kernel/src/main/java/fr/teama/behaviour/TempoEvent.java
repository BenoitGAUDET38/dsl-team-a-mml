package fr.teama.behaviour;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;

public class TempoEvent {
    public static MidiEvent createTempoEvent(int bpm, long tick) {
        return new MidiEvent(tempoMessage(bpm), tick);
    }

    private static MetaMessage tempoMessage(int bpm) {
        int MICROSECONDS_PER_MINUTE = 60000000;
        int microSecPerBeat = MICROSECONDS_PER_MINUTE / bpm;
        byte TEMPO_MIDI_SUBTYPE = 0x51;
        MetaMessage tempoMessage = null;
        try {
            tempoMessage = new MetaMessage(TEMPO_MIDI_SUBTYPE, new byte[] {
                    (byte) ((microSecPerBeat >>> 16) & 0xFF),
                    (byte) ((microSecPerBeat >>> 8 ) & 0xFF),
                    (byte) ((microSecPerBeat       ) & 0xFF)
            }, 3);
        } catch (InvalidMidiDataException ignored) {
            /* Will never happen as the message type is a defined constant */
        }
        return tempoMessage;
    }
}
