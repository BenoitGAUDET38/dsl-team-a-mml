package fr.teama.structural.classic;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.NoteDurationEnum;
import fr.teama.structural.interfaces.Note;
import fr.teama.structural.enums.ClassicNoteEnum;

public class ClassicNote implements Visitable, Note {

    NoteDurationEnum noteDurationEnum;
    ClassicNoteEnum classicNoteEnum;

    public ClassicNote(ClassicNoteEnum classicNoteEnum, NoteDurationEnum noteDurationEnum) {
        this.classicNoteEnum = classicNoteEnum;
        this.noteDurationEnum = noteDurationEnum;
    }

    public ClassicNoteEnum getClassicNoteEnum() {
        return classicNoteEnum;
    }

    public void setClassicNoteEnum(ClassicNoteEnum classicNoteEnum) {
        this.classicNoteEnum = classicNoteEnum;
    }

    @Override
    public NoteDurationEnum getNoteDurationEnum() {
        return this.noteDurationEnum;
    }



    @Override
    public String toString() {
        return "Note{" +
                "note=" + classicNoteEnum +
                ", duration=" + noteDurationEnum +
                '}';
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
