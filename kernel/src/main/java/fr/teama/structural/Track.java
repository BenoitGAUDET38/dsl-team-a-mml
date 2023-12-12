package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.List;

public class Track implements Visitable {
    List<Note> notes;
    private InstrumentEnum instrument;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public InstrumentEnum getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentEnum instrument) {
        this.instrument = instrument;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
