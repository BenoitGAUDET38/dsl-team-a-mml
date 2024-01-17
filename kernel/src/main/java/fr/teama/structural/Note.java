package fr.teama.structural;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.generator.Visitable;
import fr.teama.generator.Visitor;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Note extends NamedElement implements Visitable, Cloneable {

    NoteDurationEnum noteDurationEnum;
    int noteNumber;

    Optional<Integer> tick = Optional.empty();

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum) {
        this.noteNumber = noteNumber;
        this.noteDurationEnum = noteDurationEnum;
    }

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum, String name) {
        this.noteNumber = noteNumber;
        this.noteDurationEnum = noteDurationEnum;
        setName(name);
    }

    public Note(int noteNumber, NoteDurationEnum noteDurationEnum, Double timing, int unityTimeValue) throws InvalidTickException {
        this.noteDurationEnum = noteDurationEnum;
        this.noteNumber = noteNumber;
        double tick;
        try{
            tick = (timing*(4/(Math.pow(2, unityTimeValue/4))));
            //tick = timing * 4;
        }
        catch (Exception e){
            throw new InvalidTickException("Invalid unity time value");
        }
        if (tick % 1 == 0)
            this.tick = Optional.of((int) tick);
        else
            throw new InvalidTickException("Invalid tick for note");
    }
    public Note(int noteNumber, NoteDurationEnum noteDurationEnum, int timing, int unityTimeValue) throws InvalidTickException {
        this.noteDurationEnum = noteDurationEnum;
        this.noteNumber = noteNumber;
        double tick;
        int power = 8;
        if (unityTimeValue==4)
            power = 0;
        try{
            tick = (timing*(4/(Math.pow(2, unityTimeValue/8))));
            //tick = timing * 4;
            System.out.println(" tick : "+tick);
        }
        catch (Exception e){
            throw new InvalidTickException("Invalid unity time value");
        }
        if (tick % 1 == 0)
            this.tick = Optional.of((int) tick);
        else
            throw new InvalidTickException("Invalid tick for note");
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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteNumber=" + noteNumber +
                ", duration=" + noteDurationEnum +
                ", tick=" + tick +
                '}';
    }
}
