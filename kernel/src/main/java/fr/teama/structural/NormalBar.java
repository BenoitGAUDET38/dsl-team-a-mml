package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalBar extends Bar implements Cloneable, Visitable {
    private int tempo;
    private int resolution;
    private int unityTimeValue;
    private List<Note> notes;

    public NormalBar(int tempo, int resolution,int unityTimeValue) {
        super();
        this.tempo = tempo;
        this.resolution = resolution;
        this.notes = new ArrayList<>();
        this.unityTimeValue = unityTimeValue;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public double numberOfTicksInBar(){
        return resolution * 4;
    }

    public void addNote(int position, Note note) {
        this.notes.add(position, note);
    }

    public void modifyNote(int position, Note note) {
        this.notes.remove(position);
        this.notes.add(position, note);
    }

    public void removeNote(int start, Optional<Integer> end) {
        if (end.isEmpty()) {
            this.notes.remove(start);
        } else {
            if (end.get() >= start) {
                this.notes.subList(start, end.get() + 1).clear();
            }
        }

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

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void accept(Visitor visitor) throws InconsistentBarException {
        visitor.visit(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        NormalBar clonedObject = (NormalBar) super.clone();

        // Clone the optional name field
        clonedObject.name = this.name.map(String::new);

        // Clone the list of notes
        clonedObject.notes = new ArrayList<>();
        for (Note note : this.notes) {
            clonedObject.notes.add((Note) note.clone());
        }

        return clonedObject;
    }

    public int getUnityTimeValue() {
        return unityTimeValue;
    }

    public void setUnityTimeValue(int unityTimeValue) {
        this.unityTimeValue = unityTimeValue;
    }

    @Override
    public String toString() {
        return "NormalBar{" +
                "tempo=" + tempo +
                ", resolution=" + resolution +
                ", notes=" + notes +
                ", name='" + name +
                '}';
    }
}
