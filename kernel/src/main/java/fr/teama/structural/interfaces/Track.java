package fr.teama.structural.interfaces;

import fr.teama.generator.Visitor;
import fr.teama.structural.classic.ClassicInstrument;
import fr.teama.structural.classic.ClassicMeasure;

public interface Track {
    void setInstrument(ClassicInstrument instrument);

    Measure getMeasure();

    Instrument getInstrument();

    void setMeasure(ClassicMeasure measure);

    void accept(Visitor visitor);

}
