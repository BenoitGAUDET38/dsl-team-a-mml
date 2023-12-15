package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.List;

public abstract class Track implements Visitable {
    private InstrumentEnum instrument;

    public InstrumentEnum getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentEnum instrument) {
        this.instrument = instrument;
    }
}
