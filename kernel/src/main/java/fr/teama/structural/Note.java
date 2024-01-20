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

    public Note(NoteNumber noteNumber, NoteDurationEnum noteDuration, int tick) throws InvalidTickException {
        this.noteDuration = noteDuration;
        this.noteNumber = noteNumber;
        this.tick = Optional.of(tick);
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
                ", noteName=" + name +
                ", duration=" + noteDuration +
                ", tick=" + tick +
                ", octave=" + octave +
                '}';
    }
}
