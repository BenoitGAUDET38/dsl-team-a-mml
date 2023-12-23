package fr.teama.structural.classic;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.interfaces.Track;

public class ClassicTrack implements Visitable, Track {
    private ClassicInstrument instrument;
    private ClassicMeasure measure;

    public ClassicTrack() {
    }

    @Override
    public ClassicInstrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument(ClassicInstrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public ClassicMeasure getMeasure() {
        return measure;
    }

    @Override
    public void setMeasure(ClassicMeasure measure) {
        this.measure = measure;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Track{" +
                "measure=" + measure +
                ", instrument=" + instrument +
                '}';
    }
}
