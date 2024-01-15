package fr.teama.structural;

import fr.teama.generator.Visitable;

public abstract class Bar extends NamedElement implements Visitable {

    @Override
    public String toString() {
        return "Bar{" +
                "name=" + name +
                '}';
    }
}
