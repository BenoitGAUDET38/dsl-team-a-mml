package fr.teama.structural.manipulations;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.structural.Manipulation;
import fr.teama.structural.NormalBar;
import fr.teama.structural.Note;
import fr.teama.structural.NoteNumber;
import fr.teama.structural.enums.NoteDurationEnum;

public class AddManipulation extends Manipulation {
    Note note;

    public AddManipulation(NoteNumber noteNumber, NoteDurationEnum noteDurationEnum, Double timing) throws InvalidTickException {
        this.note = new Note(
                noteNumber,
                noteDurationEnum,
                timing
        );
    }

    public AddManipulation(NoteNumber noteNumber, NoteDurationEnum noteDurationEnum, Double timing, String noteName) throws InvalidTickException {
        this.note = new Note(
                noteNumber,
                noteDurationEnum,
                timing
        );
        this.note.setName(noteName);
    }

    @Override
    public void apply(NormalBar bar) {
        bar.addNote(note);
    }
}
