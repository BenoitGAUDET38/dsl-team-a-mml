package fr.teama.structural;

import fr.teama.generator.Visitor;

import java.util.List;

public class TrackDrum extends Track {
    List<NoteDrum> drumNotes;

    public TrackDrum() {
        super();
        this.setInstrument(InstrumentEnum.DRUM);
    }

    public List<NoteDrum> getDrumNotes() {
        return drumNotes;
    }

    public void setDrumNotes(List<NoteDrum> drumNotes) {
        this.drumNotes = drumNotes;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
