package fr.teama.generator;

import fr.teama.App;
import fr.teama.structural.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(TrackPiano trackPiano);
	public abstract void visit(TrackDrum trackDrum);
	public abstract void visit(Note note);
	public abstract void visit(NoteDrum noteDrum);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

