package fr.teama;

import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.NamedElement;
import fr.teama.structural.Track;

import java.util.List;

public class App extends NamedElement implements Visitable {

	List<Track> tracks;

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
				"\nname='" + getName() + '\'' +
				"\ntracks=" + tracks +
				'}';
	}
}
