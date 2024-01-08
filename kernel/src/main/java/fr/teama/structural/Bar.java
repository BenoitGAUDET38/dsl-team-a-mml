package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bar implements Visitable, Cloneable {
    private Optional<String> name;
    private int tempo;
    private int resolution;
    private List<Note> notes;

    public Bar(int tempo, int resolution) {
        this.name = Optional.of("noName");
        this.tempo = tempo;
        this.resolution = resolution;
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        this.notes.add(note);
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
            for (int i = end.get(); i >= start; i--) {
                this.notes.remove(i);
            }            
        }
        
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
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
    public Object clone() throws CloneNotSupportedException {
        Bar clonedObject = (Bar) super.clone();

        // Clone the optional name field
        clonedObject.name = this.name.isPresent() ? Optional.of(new String(this.name.get())) : Optional.empty();

        // Clone the list of notes
        clonedObject.notes = new ArrayList<>();
        for (Note note : this.notes) {
            clonedObject.notes.add((Note) note.clone());
        }

        return clonedObject;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name=" + name.get() +
                ", tempo=" + tempo +
                ", resolution=" + resolution +
                ", notes=" + notes +
                '}';
    }
}
