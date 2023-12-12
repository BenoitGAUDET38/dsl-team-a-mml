package fr.teama.structural;

public enum InstrumentEnum {
    PIANO(0),
    DRUM(9);

    private final int instrumentChannelNumber;

    InstrumentEnum(int instrumentChannelNumber) {
        this.instrumentChannelNumber = instrumentChannelNumber;
    }

    public int getInstrumentChannelNumber() {
        return instrumentChannelNumber;
    }
}
