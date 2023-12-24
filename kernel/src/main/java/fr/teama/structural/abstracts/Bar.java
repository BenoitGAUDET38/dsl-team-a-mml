package fr.teama.structural.abstracts;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitor;

import java.util.List;

public abstract class Bar {
    private int tempo;
    private int resolution;
    private List<Note> notes;

    public Bar(int tempo, int resolution, List<Note> notes) {
        this.tempo = tempo;
        this.resolution = resolution;
        this.notes = notes;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getResolution() {
        return resolution;
    }

    void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public List<Note> getNotes() {
        return notes;
    }

    void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void accept(Visitor visitor) throws InconsistentBarException {
        visitor.visit(this);
    }
}
