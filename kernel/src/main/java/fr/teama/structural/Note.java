package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

public class Note implements Visitable {
    private NoteNumber note;
    private NoteDurationEnum duration;

    public Note() {
    }

    public Note(NoteEnum note, NoteDurationEnum duration) {
        this.note = note;
        this.duration = duration;
    }

    public NoteNumber getNote() {
        return note;
    }

    public void setNote(NoteEnum note) {
        this.note = note;
    }

    public NoteDurationEnum getDuration() {
        return duration;
    }

    public void setDuration(NoteDurationEnum duration) {
        this.duration = duration;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
