package fr.teama.generator;

import fr.teama.App;
import fr.teama.exceptions.InconsistentBarException;
import fr.teama.exceptions.NoRootNormalBarFoundException;
import fr.teama.structural.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Track track);
	public abstract void visit(NormalBar normalBar) throws InconsistentBarException;
	public abstract void visit(ReusedBar reusedBar) throws InconsistentBarException, CloneNotSupportedException, NoRootNormalBarFoundException;

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

