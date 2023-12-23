package fr.teama.structural.classic;

import fr.teama.structural.interfaces.Instrument;

public class ClassicInstrument implements Instrument {
    int instrumentChannelNumber;

    public ClassicInstrument() {

    }

    @Override
    public int getInstrumentChannelNumber() {
        return instrumentChannelNumber;
    }
}
