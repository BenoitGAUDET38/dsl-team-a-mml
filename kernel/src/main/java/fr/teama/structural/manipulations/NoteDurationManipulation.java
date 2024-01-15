package fr.teama.structural.manipulations;

import fr.teama.structural.Manipulation;
import fr.teama.structural.NormalBar;
import fr.teama.structural.enums.InstrumentEnum;
import fr.teama.structural.enums.NoteDurationEnum;

public class NoteDurationManipulation extends Manipulation {
    String noteName;
    NoteDurationEnum noteDuration;

    public NoteDurationManipulation(String noteName, NoteDurationEnum noteDuration) {
        this.noteName = noteName;
        this.noteDuration = noteDuration;
    }

    @Override
    public void apply(NormalBar bar) {
        bar.getNotes().stream()
                .filter(note -> note.getName().isPresent() && note.getName().get().equals(noteName))
                .forEach(note -> note.setNoteDurationEnum(noteDuration));
    }
}
