package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

public class Note implements Visitable {
    private NoteNumber note;
    private int tick;
    private NoteDurationEnum duration;

    public Note() {
    }

    public Note(NoteEnum note, int tick) {
        this.note = note;
        this.tick = tick;
    }

    public NoteNumber getNote() {
        return note;
    }

    public void setNote(NoteNumber note) {
        this.note = note;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public NoteDurationEnum getDuration() {
        return duration;
    }

    public void setDuration(NoteDurationEnum duration) {
        this.tick += duration.getDuration();
        this.duration = duration;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
