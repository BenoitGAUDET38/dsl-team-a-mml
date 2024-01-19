package fr.teama.structural.manipulations;

import fr.teama.structural.*;

import java.util.List;

public class NoteNumberManipulation extends Manipulation {
    String noteName;
    NoteNumber noteNumber;

    public NoteNumberManipulation(String noteName, NoteNumber noteNumber) {
        this.noteName = noteName;
        this.noteNumber = noteNumber;
    }

    @Override
    public void apply(NormalBar bar) {
        List<Note> notes = bar.getNotes().stream()
                .filter(note -> note.getName().isPresent() && note.getName().get().equals(noteName))
                .toList();

        if (notes.isEmpty()) {
            System.out.println("\033[33m" + "WARNING: Note " + noteName + " not found in bar " + bar + ", note number modification not applied" + "\033[0m");
            return;
        }

        notes.forEach(note -> note.setNoteNumber(noteNumber));
    }
}
