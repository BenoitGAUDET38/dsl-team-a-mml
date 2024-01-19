package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.exceptions.NoRootNormalBarFoundException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.List;

public class ReusedBar extends Bar implements Visitable {
    int repetition;
    List<Manipulation> manipulations;
    Bar bar;

    public ReusedBar(int repetition, Bar bar, List<Manipulation> manipulations, int tempo, int resolution, int unityTimeValue) {
        super(tempo, resolution, unityTimeValue);
        this.repetition = repetition;
        this.manipulations = manipulations;
        this.bar = bar;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public List<Manipulation> getManipulations() {
        return manipulations;
    }

    public void setManipulations(List<Manipulation> manipulations) {
        this.manipulations = manipulations;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void accept(Visitor visitor) throws InconsistentBarException, NoRootNormalBarFoundException, CloneNotSupportedException {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "ReusedBar{" +
                "repetition=" + repetition +
                ", manipulations=" + manipulations +
                ", bar=" + bar +
                "name=" + getName() +
                '}';
    }
}
