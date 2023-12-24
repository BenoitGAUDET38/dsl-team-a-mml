package fr.teama.structural.drum;

import fr.teama.generator.Visitable;
import fr.teama.structural.abstracts.Note;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

public class DrumNote extends Note implements Visitable {

    public DrumNote(DrumNoteEnum drumNoteEnum, NoteDurationEnum noteDurationEnum) {
        super(drumNoteEnum, noteDurationEnum);
    }
}
