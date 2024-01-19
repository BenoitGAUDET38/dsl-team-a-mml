package fr.teama.structural.enums;

public enum InstrumentEnum {
    DRUM(-1),
    PIANO(0),
    XYLOPHONE(13),
    ACCORDION(21),
    HARMONICA(22),
    GUITAR(24),
    ELECTRIC_GUITAR_OVERDRIVE(29),
    BASS(32),
    ELECTRIC_BASS_PICKED(34),
    ELECTRIC_BASS_FRETLESS(35),
    VIOLIN(40),
    TRUMPET(56),
    TROMBONE(57),
    ALTO_SAX(65),
    CLARINET(71),
    FLUTE(73),
    WHISTLE(78),
    OCARINA(79),
    BANJO(105);

    private int instrumentNumber;

    private InstrumentEnum(int instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public int getInstrumentNumber() {
        return instrumentNumber;
    }
}
