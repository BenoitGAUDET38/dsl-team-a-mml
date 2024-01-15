package fr.teama.structural;

import java.util.Optional;

public abstract class NamedElement {

    Optional<String> name = Optional.empty();

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
    }
}
