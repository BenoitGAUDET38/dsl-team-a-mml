package fr.teama.structural.interfaces;

import fr.teama.structural.classic.ClassicNote;

import java.util.List;

public interface Measure {
    int getTempo();

    void setTempo(int tempo);

    int getResolution();

    void setResolution(int resolution);

    List<ClassicNote> getNotes();

    void setNotes(List<ClassicNote> notes);
}
