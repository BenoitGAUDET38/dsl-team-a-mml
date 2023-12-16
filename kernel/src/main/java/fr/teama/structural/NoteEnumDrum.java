package fr.teama.structural;

public enum NoteEnumDrum implements NoteNumber{
    B(71),
    BD(36),
    SD(38),
    CH(42),
    OH(46),
    CC(49),
    RC(51);

    private final int noteNumber;

    NoteEnumDrum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getNoteNumber() {
        return noteNumber;
    }
}
