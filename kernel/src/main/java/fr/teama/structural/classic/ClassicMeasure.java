package fr.teama.structural.classic;

import fr.teama.structural.interfaces.Measure;
import fr.teama.structural.interfaces.Note;

import java.util.List;

public class ClassicMeasure implements Measure {
    private int tempo;
    private int resolution;
    private List<ClassicNote> notes;

    public ClassicMeasure() {
        this.tempo = 120;
        this.resolution = 4;
    }

    @Override
    public int getTempo() {
        return tempo;
    }

    @Override
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public int getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public List<ClassicNote> getNotes() {
        return notes;
    }

    @Override
    public void setNotes(List<ClassicNote> notes) {
        this.notes = notes;
    }
}
