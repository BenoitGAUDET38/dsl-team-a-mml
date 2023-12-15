package fr.teama.structural;

public class Beat {
    private int tempo;
    private int resolution;

    public Beat() {
        this.tempo = 120;
        this.resolution = 4;
    }

    public Beat(int tempo, int resolution) {
        this.tempo = tempo;
        this.resolution = resolution;
    }

    public int getTempo() {
        return tempo;
    }

    public int getResolution() {
        return resolution;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}
