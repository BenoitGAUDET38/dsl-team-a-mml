package fr.teama;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.Track;

import java.util.List;

public class App extends NamedElement implements Visitable  {

	List<Track> tracks;

	private int tempo;

	private int resolution;

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
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

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "App{" +
				"name='" + name + '\'' +
				"\ntracks=" + tracks +
				"\ntempo=" + tempo +
				"\nresolution=" + resolution +
				'}';
	}
}
