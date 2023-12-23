package fr.teama.structural.interfaces;

import fr.teama.generator.Visitor;
import fr.teama.structural.enums.NoteDurationEnum;

public interface Note {
    NoteDurationEnum getNoteDurationEnum();

    void accept(Visitor visitor);
}
