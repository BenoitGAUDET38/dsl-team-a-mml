package fr.teama.structural.manipulations;

import fr.teama.structural.Manipulation;
import fr.teama.structural.NormalBar;
import fr.teama.structural.Note;
import fr.teama.structural.enums.NoteDurationEnum;

import java.util.List;

public class NoteDurationManipulation extends Manipulation {
    String noteName;
    NoteDurationEnum noteDuration;

    public NoteDurationManipulation(String noteName, NoteDurationEnum noteDuration) {
        this.noteName = noteName;
        this.noteDuration = noteDuration;
    }

    @Override
    public void apply(NormalBar bar) {
        List<Note> notes = bar.getNotes().stream()
                .filter(note -> note.getName().isPresent() && note.getName().get().equals(noteName))
                .toList();

        if (notes.isEmpty()) {
            System.out.println("\033[33m" + "WARNING: Note " + noteName + " not found in bar " + bar.getName() + ", note duration modification not applied" + "\033[0m");
            return;
        }

        notes.forEach(note -> note.setNoteDuration(noteDuration));
    }
}
