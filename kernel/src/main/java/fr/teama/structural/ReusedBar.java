package fr.teama.structural;

public class ReusedBar extends Bar {
    ReusedBar reusedBar;
    int repetition;

    public ReusedBar(ReusedBar reusedBar, int repetition, int tempo, int resolution) {
        super();
        this.reusedBar = reusedBar;
        this.repetition = repetition;
    }
}
