package fr.teama.structural.abstracts;

import fr.teama.generator.Visitor;

import java.util.List;

public abstract class Track {
    private Instrument instrument;
    private List<Bar> bars;

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Track{" +
                "bars=" + bars +
                ", instrument=" + instrument +
                '}';
    }

}
