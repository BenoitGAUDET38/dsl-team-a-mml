package fr.teama.structural.drum;

import fr.teama.structural.abstracts.Bar;
import fr.teama.structural.abstracts.Note;

import java.util.List;

public class DrumBar extends Bar {

    public DrumBar(int tempo, int resolution, List<Note> notes) {
        super(tempo, resolution, notes);
    }
}
