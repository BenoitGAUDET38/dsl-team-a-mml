package fr.teama.structural;

public enum NoteEnum implements NoteNumber{
    // Music note with associated number
    DO(60),
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
    SI(71);




    private final int noteNumber;

    NoteEnum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getNoteNumber() {
        return noteNumber;
    }
}
