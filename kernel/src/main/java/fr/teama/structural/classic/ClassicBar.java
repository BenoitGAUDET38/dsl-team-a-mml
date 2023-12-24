package fr.teama.structural.classic;

import fr.teama.structural.abstracts.Bar;
import fr.teama.structural.abstracts.Note;

import java.util.List;

public class ClassicBar extends Bar {

    public ClassicBar(int tempo, int resolution, List<Note> notes) {
        super(tempo, resolution, notes);
    }
}
