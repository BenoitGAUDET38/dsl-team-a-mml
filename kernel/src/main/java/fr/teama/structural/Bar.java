package fr.teama.structural;

import fr.teama.generator.Visitable;

public abstract class Bar extends NamedElement implements Visitable {
    int tempo;
    int resolution;
    int unityTimeValue;

    public Bar(int tempo, int resolution, int unityTimeValue) {
        this.tempo = tempo;
        this.resolution = resolution;
        this.unityTimeValue = unityTimeValue;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public int getUnityTimeValue() {
        return unityTimeValue;
    }

    public void setUnityTimeValue(int unityTimeValue) {
        this.unityTimeValue = unityTimeValue;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "name=" + name +
                ", tempo=" + tempo +
                ", resolution=" + resolution +
                ", unityTimeValue=" + unityTimeValue +
                '}';
    }
}
