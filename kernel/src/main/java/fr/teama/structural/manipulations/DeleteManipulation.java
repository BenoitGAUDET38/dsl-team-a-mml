package fr.teama.structural.manipulations;

import fr.teama.structural.Manipulation;
import fr.teama.structural.NormalBar;
import fr.teama.structural.Note;

import java.util.ArrayList;
import java.util.List;

public class DeleteManipulation extends Manipulation {
    String noteName;

    public DeleteManipulation(String noteName) {
        this.noteName = noteName;
    }

    @Override
    public void apply(NormalBar bar) {
        List<Note> notesToDelete = new ArrayList<>();
        bar.getNotes().stream()
                .filter(note -> note.getName().isPresent() && note.getName().get().equals(noteName))
                .forEach(notesToDelete::add);

        if (notesToDelete.isEmpty()) {
            System.out.println("\033[33m" + "WARNING: Note " + noteName + " not found in bar " + bar.getName() + ", delete modification not applied" + "\033[0m");
            return;
        }

        bar.getNotes().removeAll(notesToDelete);
    }
}
