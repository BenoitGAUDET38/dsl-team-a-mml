package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void titanic() {
        App app = new App();
        Beat beat = new Beat();
        beat.setTempo(100);
        beat.setResolution(4);
        app.setBeat(beat);
        app.setName("titanic_piano_simple_lent");
        List<Track> tracks = new ArrayList<>();
        app.setTracks(tracks);

        Track track = new Track();
        tracks.add(track);

        List<Note> notes = new ArrayList<>();
        track.setNotes(notes);

        // Partition de test du Titanic
        // Ligne 1
        notes.add(new Note(NoteEnum.FA, 3));
        notes.add(new Note(NoteEnum.FA, 1));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.SOL, 2));
        notes.add(new Note(NoteEnum.LA, 4));
        notes.add(new Note(NoteEnum.SOL, 4));

        // Ligne 2
        notes.add(new Note(NoteEnum.FA, 3));
        notes.add(new Note(NoteEnum.FA, 1));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.DO, 8));
        notes.add(new Note(null, 8));

        // Ligne 3
        notes.add(new Note(NoteEnum.FA, 3));
        notes.add(new Note(NoteEnum.FA, 1));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.SOL, 2));
        notes.add(new Note(NoteEnum.LA, 4));
        notes.add(new Note(NoteEnum.SOL, 4));

        // Ligne 4
        notes.add(new Note(NoteEnum.FA, 3));
        notes.add(new Note(NoteEnum.FA, 1));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.MI, 2));
        notes.add(new Note(NoteEnum.FA, 4));
        notes.add(new Note(NoteEnum.FA, 2));
        notes.add(new Note(NoteEnum.DO, 8));
        notes.add(new Note(null, 4));
        notes.add(new Note(NoteEnum.RE, 2));
        notes.add(new Note(NoteEnum.MI, 2));


        notes = new ArrayList<>();

        notes.add(new Note(NoteEnum.FA, 8));
        notes.add(new Note(NoteEnum.FA, 8));
        notes.add(new Note(NoteEnum.FA, 8));
        notes.add(new Note(NoteEnum.FA, 8));
        notes.add(new Note(NoteEnum.FA, 8));
        notes.add(new Note(NoteEnum.FA, 8));

        System.out.println(app.getTracks());

        app.accept(new ToWiring());
    }

    public static void basic() {
        App app = new App();
        Beat beat = new Beat();
        beat.setTempo(100);
        beat.setResolution(4);
        app.setBeat(beat);
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
        basic();
    }
}
