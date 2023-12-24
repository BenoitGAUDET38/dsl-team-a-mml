package fr.teama.structural.abstracts;

import fr.teama.generator.Visitor;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

public abstract class Note {

    NoteDurationEnum noteDurationEnum;
    ClassicNoteEnum classicNoteEnum;
    DrumNoteEnum drumNoteEnum;

    public Note(ClassicNoteEnum classicNoteEnum, NoteDurationEnum noteDurationEnum) {
        this.classicNoteEnum = classicNoteEnum;
        this.noteDurationEnum = noteDurationEnum;
    }

    public Note(DrumNoteEnum drumNoteEnum, NoteDurationEnum noteDurationEnum) {
        this.drumNoteEnum = drumNoteEnum;
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

    public DrumNoteEnum getDrumNoteEnum() {
        return drumNoteEnum;
    }

    public void setDrumNoteEnum(DrumNoteEnum drumNoteEnum) {
        this.drumNoteEnum = drumNoteEnum;
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
