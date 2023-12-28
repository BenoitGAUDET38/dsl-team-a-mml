package fr.teama.structural;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Note implements Visitable {

    NoteDurationEnum noteDurationEnum;
    int noteNumber;

    Optional<Integer> tick = Optional.empty();

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum) {
        this.noteNumber = noteNumber;
        this.noteDurationEnum = noteDurationEnum;
    }

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum, Double timing) throws InvalidTickException {
        this.noteDurationEnum = noteDurationEnum;
        this.noteNumber = noteNumber;
        if ((timing*4) % 1 == 0)
            this.tick = Optional.of((int) (timing*4));
        else
            throw new InvalidTickException("Invalid tick for note");
    }
    public Note(int noteNumber, NoteDurationEnum noteDurationEnum, int timing) throws InvalidTickException {
        this.noteDurationEnum = noteDurationEnum;
        this.noteNumber = noteNumber;
        this.tick = Optional.of(timing*4);
    }

    public NoteDurationEnum getNoteDurationEnum() {
        return this.noteDurationEnum;
    }

    public void setNoteDurationEnum(NoteDurationEnum noteDurationEnum) {
        this.noteDurationEnum = noteDurationEnum;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Optional<Integer> getTick() {
        return tick;
    }

    public void setTick(Optional<Integer> tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteNumber=" + noteNumber +
                ", duration=" + noteDurationEnum +
                '}';
    }
}
