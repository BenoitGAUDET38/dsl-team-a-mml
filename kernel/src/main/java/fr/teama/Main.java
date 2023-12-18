package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void titanic() {
        App app = new App();
        app.setTempo(100);
        app.setResolution(4);
        app.setName("titanic_piano_simple_lent");
        List<Track> tracks = new ArrayList<>();
        app.setTracks(tracks);

        Track track = new Track();
        tracks.add(track);

        List<Note> notes = new ArrayList<>();
        track.setNotes(notes);

        // Partition de test du Titanic
        // Ligne 1
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 2
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.DO, NoteDurationEnum.R));
        notes.add(new Note(null, NoteDurationEnum.BL));

        // Ligne 3
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 4
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.DO, NoteDurationEnum.R));
        notes.add(new Note(null, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.RE, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));

        System.out.println(app.getTracks());

        app.accept(new ToWiring());
    }

    public static void basic() {
        App app = new App();
        app.setTempo(100);
        app.setResolution(4);
        app.setName("titanic_piano_simple_lent");
        List<Track> tracks = new ArrayList<>();
        app.setTracks(tracks);

        Track track = new Track();
        tracks.add(track);

        List<Note> notes = new ArrayList<>();
        track.setNotes(notes);
        Note note = new Note();
        note.setNote(NoteEnum.DO);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.RE);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.MI);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.FA);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.SOL);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.LA);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);
        note = new Note();
        note.setNote(NoteEnum.SI);
        note.setDuration(NoteDurationEnum.N);
        notes.add(note);

        app.accept(new ToWiring());
    }

    public static void main(String[] args) {
        titanic();
    }
}
