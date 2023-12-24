package fr.teama.generator;

import fr.teama.App;
import fr.teama.structural.abstracts.Bar;
import fr.teama.structural.abstracts.Note;
import fr.teama.structural.abstracts.Track;
import fr.teama.exceptions.InconsistentBarException;
import fr.teama.structural.classic.ClassicNote;
import fr.teama.structural.drum.DrumNote;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Track track);
	public abstract void visit(Bar bar) throws InconsistentBarException;
	public abstract void visit(Note note);
	public abstract void visit(ClassicNote note);
	public abstract void visit(DrumNote note);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}


}

