package fr.teama.structural.enums;

import fr.teama.structural.NoteNumber;

public enum ClassicNoteEnum implements NoteNumber {
    // Music note with associated number
    DO(60), // middle C, do3 in French notation, C4 in American notation
    DO_D(61),
    RE(62),
    RE_D(63),
    MI(64),
    FA(65),
    FA_D(66),
    SOL(67),
    SOL_D(68),
    LA(69),
    LA_D(70),
    SI(71),
    SILENCE(-1);

    private final int noteNumber;

    ClassicNoteEnum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    @Override
    public int getNoteNumber() {
        return noteNumber;
    }
}
