package fr.teama.structural.abstracts;

import fr.teama.generator.Visitor;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

public abstract class Note {

    NoteDurationEnum noteDurationEnum;
    ClassicNoteEnum classicNoteEnum;

    public Note(ClassicNoteEnum classicNoteEnum, NoteDurationEnum noteDurationEnum) {
        this.classicNoteEnum = classicNoteEnum;
        this.noteDurationEnum = noteDurationEnum;
    }

    public NoteDurationEnum getNoteDurationEnum() {
        return this.noteDurationEnum;
    }

    public void setNoteDurationEnum(NoteDurationEnum noteDurationEnum) {
        this.noteDurationEnum = noteDurationEnum;
    }

    public ClassicNoteEnum getClassicNoteEnum() {
        return classicNoteEnum;
    }

    public void setClassicNoteEnum(ClassicNoteEnum classicNoteEnum) {
        this.classicNoteEnum = classicNoteEnum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Note{" +
                "note=" + classicNoteEnum +
                ", duration=" + noteDurationEnum +
                '}';
    }
}
