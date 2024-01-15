package fr.teama.structural.manipulations;

import fr.teama.structural.Manipulation;
import fr.teama.structural.NormalBar;
import fr.teama.structural.Note;
import fr.teama.structural.PositionHandler;

import java.util.List;

public class NoteNumberManipulation extends Manipulation {
    String noteName;
    int noteNumber;

    public NoteNumberManipulation(int noteNumber, String noteName) {
        super();
        this.noteName = noteName;
        this.noteNumber = noteNumber;
    }

    @Override
    public void apply(NormalBar bar) {
        bar.getNotes().stream()
                .filter(note -> note.getName().isPresent() && note.getName().get().equals(noteName))
                .forEach(note -> note.setNoteNumber(noteNumber));
    }
}
