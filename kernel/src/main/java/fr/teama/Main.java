package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.classic.*;
import fr.teama.structural.classic.instruments.Piano;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;
import fr.teama.structural.interfaces.Track;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void titanic() {
        App app = new App();
        app.setName("titanic_piano_simple_lent");

        List<Track> tracks = new ArrayList<>();
        ClassicTrack track = new ClassicTrack();
        ClassicInstrument piano = new Piano();
        ClassicMeasure measure = new ClassicMeasure();
        List<ClassicNote> notes = new ArrayList<>();

        measure.setTempo(100);
        measure.setResolution(4);
        measure.setNotes(notes);
        track.setMeasure(measure);
        track.setInstrument(piano);
        tracks.add(track);
        app.setTracks(tracks);

        // Partition de test du Titanic
        // Ligne 1
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.D_C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.LA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.N));

        // Ligne 2
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.D_C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.DO, NoteDurationEnum.BL));
        notes.add(new ClassicNote(null, NoteDurationEnum.BL));

        // Ligne 3
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.D_C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.LA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.N));

        // Ligne 4
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.D_C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.DO, NoteDurationEnum.BL));
        notes.add(new ClassicNote(null, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.RE, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.C));


        notes = new ArrayList<>();

        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));

        System.out.println(app.getTracks());

        app.accept(new ToWiring());
    }

    /*public static void basic() {
        App app = new App();
        Measure measure = new Measure();
        measure.setTempo(100);
        measure.setResolution(4);
        app.setBeat(measure);
        app.setName("titanic_piano_simple_lent");
        List<ClassicTrack> tracks = new ArrayList<>();
        app.setTracks(tracks);

        ClassicTrack track = new ClassicTrack();
        tracks.add(track);

        List<Note> notes = new ArrayList<>();
        track.setNotes(notes);
        Note note = new Note();
        note.setNote(ClassicNoteEnum.DO);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.RE);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.MI);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.FA);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.SOL);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.LA);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.SI);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);

        app.accept(new ToWiring());

    }*/




    public static void main(String[] args) {
        titanic();
    }
}
