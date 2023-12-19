package fr.teama.generator;

import fr.teama.exceptions.InconsistentBarException;

public interface Visitable {

	void accept(Visitor visitor) throws InconsistentBarException;

}
