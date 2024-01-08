package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

public class Note implements Visitable, Cloneable {

    NoteDurationEnum noteDurationEnum;
    int noteNumber;

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum) {
        this.noteNumber = noteNumber;
        this.noteDurationEnum = noteDurationEnum;
    }

    public NoteDurationEnum getNoteDurationEnum() {
        return this.noteDurationEnum;
    }

    public void setNoteDurationEnum(NoteDurationEnum noteDurationEnum) {
        this.noteDurationEnum = noteDurationEnum;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteNumber=" + noteNumber +
                ", duration=" + noteDurationEnum +
                '}';
    }
}
