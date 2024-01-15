package fr.teama.structural.manipulations;

import fr.teama.structural.*;

public class NoteNumberManipulation extends Manipulation {
    String noteName;
    NoteNumber noteNumber;

    public NoteNumberManipulation(String noteName, NoteNumber noteNumber) {
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
