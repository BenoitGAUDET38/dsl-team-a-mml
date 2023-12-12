package fr.teama;

import javax.sound.midi.*;

public class SynthTest {
    public static void main(String[] args) {
        try {
            int maxVolume = 90;
            int sound = 65;
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel channel = synthesizer.getChannels()[9];  // drums channel.
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                channel.noteOn(sound + i, maxVolume);
                Thread.sleep(100);
                channel.noteOff(sound + i);
            }
        } catch (MidiUnavailableException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
