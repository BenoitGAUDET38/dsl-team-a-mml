package fr.teama.generator;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.exceptions.NoRootNormalBarFoundException;

public interface Visitable {

	void accept(Visitor visitor) throws InconsistentBarException, NoRootNormalBarFoundException, CloneNotSupportedException;

}
