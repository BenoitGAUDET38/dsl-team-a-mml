package fr.teama.structural.enums;

import fr.teama.structural.interfaces.NoteNumber;

public enum DrumNoteEnum implements NoteNumber {
    B(71),
    BD(36),
    SD(38),
    CH(42),
    OH(46),
    CC(49),
    RC(51);

    private final int noteNumber;

    DrumNoteEnum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getNoteNumber() {
        return noteNumber;
    }
}
