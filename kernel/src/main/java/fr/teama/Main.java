package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.setTempo(100);
        app.setName("titanic_piano_simple_lent");
        List<Track> tracks = new ArrayList<>();
        app.setTracks(tracks);

        TrackPiano track = new TrackPiano();
        tracks.add(track);

        List<Note> notes = new ArrayList<>();
        track.setNotes(notes);

        // Titanic Test Partition
        // ligne 1
        notes.add(new Note(NoteEnum.F, 3));
        notes.add(new Note(NoteEnum.F, 1));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.G, 2));
        notes.add(new Note(NoteEnum.A, 4));
        notes.add(new Note(NoteEnum.G, 4));

        // ligne 2
        notes.add(new Note(NoteEnum.F, 3));
        notes.add(new Note(NoteEnum.F, 1));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.C, 8));
        notes.add(new Note(null, 8));

        // ligne 3
        notes.add(new Note(NoteEnum.F, 3));
        notes.add(new Note(NoteEnum.F, 1));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.G, 2));
        notes.add(new Note(NoteEnum.A, 4));
        notes.add(new Note(NoteEnum.G, 4));

        // ligne 4
        notes.add(new Note(NoteEnum.F, 3));
        notes.add(new Note(NoteEnum.F, 1));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.E, 2));
        notes.add(new Note(NoteEnum.F, 4));
        notes.add(new Note(NoteEnum.F, 2));
        notes.add(new Note(NoteEnum.C, 8));
        notes.add(new Note(null, 4));
        notes.add(new Note(NoteEnum.D, 2));
        notes.add(new Note(NoteEnum.E, 2));

//        track = new Track();
//        track.setInstrument(InstrumentEnum.PIANO);
//        tracks.add(track);

        notes = new ArrayList<>();
//        track.setNotes(notes);

        notes.add(new Note(NoteEnum.F, 8));
        notes.add(new Note(NoteEnum.F, 8));
        notes.add(new Note(NoteEnum.F, 8));
        notes.add(new Note(NoteEnum.F, 8));
        notes.add(new Note(NoteEnum.F, 8));
        notes.add(new Note(NoteEnum.F, 8));

        System.out.println(app.getTracks());

        app.accept(new ToWiring());
    }
}
