package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bar implements Visitable {
    public Optional<String> name;

    public Bar() {
        this.name = Optional.of("noName");
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
    }

    public void addNote(Note note) {
    }

    public void addNote(int position, Note note) {
    }

    public void modifyNote(int position, Note note) {
    }

    public void removeNote(int start, Optional<Integer> end) {
    }

    public int getTempo() {
        return 0;
    }

    public void setTempo(int tempo) {
    }

    public int getResolution() {
        return 0;
    }

    public void setResolution(int resolution) {
    }

    public List<Note> getNotes() {
        return new ArrayList<>();
    }

    public void setNotes(List<Note> notes) {
    }

    public void accept(Visitor visitor) throws InconsistentBarException {
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name=" + name.get() +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
