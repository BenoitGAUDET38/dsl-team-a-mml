package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

public class Note implements Visitable {
    private NoteEnum note;
    private int tick;

    public Note() {
    }

    public Note(NoteEnum note, int tick) {
        this.note = note;
        this.tick = tick;
    }

    public NoteEnum getNote() {
        return note;
    }

    public void setNote(NoteEnum note) {
        this.note = note;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
