package fr.teama.structural;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.NoteDurationEnum;

import java.util.Optional;

public class Note extends NamedElement implements Visitable, Cloneable {

    NoteDurationEnum noteDuration;
    NoteNumber noteNumber;
    int octave = 3;

    Optional<Integer> tick = Optional.empty();

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration) {
        this.noteNumber = noteNumber;
        this.noteDuration = noteDuration;
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, int octave) {
        this.noteNumber = noteNumber;
        this.noteDuration = noteDuration;
        this.octave = octave;
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, String name) {
        this.noteNumber = noteNumber;
        this.noteDuration = noteDuration;
        setName(name);
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, int octave, String name) {
        this.noteNumber = noteNumber;
        this.noteDuration = noteDuration;
        this.octave = octave;
        setName(name);
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, Double timing) throws InvalidTickException {
        this.noteDuration = noteDuration;
        this.noteNumber = noteNumber;
        if ((timing*4) % 1 == 0)
            this.tick = Optional.of((int) (timing*4));
        else
            throw new InvalidTickException("Invalid tick for note");
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, Double timing, int octave) throws InvalidTickException {
        this.noteDuration = noteDuration;
        this.noteNumber = noteNumber;
        if ((timing*4) % 1 == 0)
            this.tick = Optional.of((int) (timing*4));
        else
            throw new InvalidTickException("Invalid tick for note");
        this.octave = octave;
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, Double timing, String name) throws InvalidTickException {
        this.noteDuration = noteDuration;
        this.noteNumber = noteNumber;
        if ((timing*4) % 1 == 0)
            this.tick = Optional.of((int) (timing*4));
        else
            throw new InvalidTickException("Invalid tick for note");
        setName(name);
    }

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, Double timing, int octave, String name) throws InvalidTickException {
        this.noteDuration = noteDuration;
        this.noteNumber = noteNumber;
        if ((timing*4) % 1 == 0)
            this.tick = Optional.of((int) (timing*4));
        else
            throw new InvalidTickException("Invalid tick for note");
        this.octave = octave;
        setName(name);
    }

    public NoteDurationEnum getNoteDuration() {
        return this.noteDuration;
    }

    public void setNoteDuration(NoteDurationEnum noteDuration) {
        this.noteDuration = noteDuration;
    }

    public NoteNumber getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(NoteNumber noteNumber) {
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

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteNumber=" + noteNumber +
                ", duration=" + noteDuration +
                ", tick=" + tick +
                ", octave=" + octave +
                '}';
    }
}
