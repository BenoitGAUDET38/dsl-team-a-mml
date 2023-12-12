package fr.teama;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.Track;

import javax.sound.midi.Sequence;
import java.util.List;

public class App implements Visitable {

	private String name;

	List<Track> tracks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "App{" +
				"\nname='" + name + '\'' +
				"\n}";
	}
}
