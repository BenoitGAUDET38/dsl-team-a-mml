package fr.teama.structural;

public enum NoteEnum {
    // Music note with associated number
    C(60),
    C_SHARP(61),
    D(62),
    D_SHARP(63),
    E(64),
    F(65),
    F_SHARP(66),
    G(67),
    G_SHARP(68),
    A(69),
    A_SHARP(70);


    private final int noteNumber;

    NoteEnum(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public int getNoteNumber() {
        return noteNumber;
    }
}
