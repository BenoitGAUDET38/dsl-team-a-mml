package fr.teama.structural.enums;

public enum InstrumentEnum {
    PIANO(0),
    DRUM(9);

    private int instrumentChannelNumber;

    private InstrumentEnum(int instrumentChannelNumber) {
        this.instrumentChannelNumber = instrumentChannelNumber;
    }

    public int getInstrumentChannelNumber() {
        return instrumentChannelNumber;
    }
}
