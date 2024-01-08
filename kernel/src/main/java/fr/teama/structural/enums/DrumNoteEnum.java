package fr.teama.structural.enums;

public enum DrumNoteEnum {
    BD(35), // Bass Drum
    SD(38), // Snare Drum
    CH(42), // Closed Hi-Hat
    PH(44), // Pedal Hi-Hat
    OH(46), // Open Hi-Hat
    CC(49), // Crash Cymbal
    RC(51), // Ride Cymbal
    MA(70), // Maracas

    SILENCE(-1);

    private final int noteNumber;

    DrumNoteEnum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getNoteNumber() {
        return noteNumber;
    }
}
