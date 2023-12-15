package fr.teama.structural;

import fr.teama.generator.Visitor;

import java.util.List;

public class TrackPiano extends Track {
    List<Note> notes;

    public TrackPiano() {
        super();
        this.setInstrument(InstrumentEnum.PIANO);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
