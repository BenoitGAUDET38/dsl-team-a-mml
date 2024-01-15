package fr.teama.structural;

import fr.teama.exceptions.InconsistentBarException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Bar extends NamedElement implements Visitable {

    @Override
    public String toString() {
        return "Bar{" +
                "name=" + name +
                '}';
    }
}
