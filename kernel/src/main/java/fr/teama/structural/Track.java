package fr.teama.structural;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.InstrumentEnum;

import java.util.List;

public class Track implements Visitable {
    private InstrumentEnum instrument;
    private List<Bar> bars;

    private int volume = 60;

    public InstrumentEnum getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentEnum instrument) {
        this.instrument = instrument;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "\nTrack{" +
                "\ninstrument=" + instrument +
                "\nvolume=" + volume +
                "\nbars=" + bars +
                '}';
    }

}
