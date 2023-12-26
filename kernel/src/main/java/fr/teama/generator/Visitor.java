package fr.teama.generator;

import fr.teama.App;
import fr.teama.exceptions.InconsistentBarException;
import fr.teama.structural.Bar;
import fr.teama.structural.Note;
import fr.teama.structural.Track;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Track track);
	public abstract void visit(Bar bar) throws InconsistentBarException;
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

