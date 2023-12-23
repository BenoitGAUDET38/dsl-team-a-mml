package fr.teama.generator;

import fr.teama.App;
import fr.teama.structural.interfaces.Note;
import fr.teama.structural.interfaces.Track;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(Track track);

	public abstract void visit(Note note);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

