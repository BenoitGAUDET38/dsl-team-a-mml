package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Bar implements Visitable {
    private int tempo;
    private int resolution;
    private List<Note> notes;

    public Bar(int tempo, int resolution) {
        this.tempo = tempo;
        this.resolution = resolution;
        this.notes = new ArrayList<>();
    }

    public void addNote (Note note) {
        this.notes.add(note);
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

    @Override
    public String toString() {
        return "Bar{" +
                "tempo=" + tempo +
                ", resolution=" + resolution +
                ", notes=" + notes +
                '}';
    }
}
