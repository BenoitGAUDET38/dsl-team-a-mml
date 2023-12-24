package fr.teama.structural.classic;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.abstracts.Note;
import fr.teama.structural.enums.NoteDurationEnum;
import fr.teama.structural.enums.ClassicNoteEnum;

public class ClassicNote extends Note implements Visitable {

    public ClassicNote(ClassicNoteEnum classicNoteEnum, NoteDurationEnum noteDurationEnum) {
        super(classicNoteEnum, noteDurationEnum);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
