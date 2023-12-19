package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void titanic() {
        App app = new App();
        app.setTempo(120);
        app.setResolution(4);
        app.setName("titanic_piano_simple_lent");
        List<Track> tracks = new ArrayList<>();
        app.setTracks(tracks);

        Track track = new Track();
        track.setInstrument(InstrumentEnum.PIANO);
        tracks.add(track);

        List<Bar> bars = new ArrayList<>();
        track.setBars(bars);

        Bar bar = new Bar();
        bars.add(bar);

        List<Note> notes = new ArrayList<>();
        bar.setNotes(notes);

        // Partition de test du Titanic
        // Ligne 1
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 2
        bar = new Bar();
        bars.add(bar);
        bar.setTempo(500); // go faster
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.DO, NoteDurationEnum.R));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(null, NoteDurationEnum.R));

        // Ligne 3
        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 4
        bar = new Bar();
        bars.add(bar);
        bar.setTempo(120); // back to normal
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.C));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(NoteEnum.DO, NoteDurationEnum.R));

        bar = new Bar();
        bars.add(bar);
        notes = new ArrayList<>();
        bar.setNotes(notes);
        notes.add(new Note(null, NoteDurationEnum.BL));
        notes.add(new Note(NoteEnum.RE, NoteDurationEnum.N));
        notes.add(new Note(NoteEnum.MI, NoteDurationEnum.N));

        // Seconde track
//        track = new Track();
//        track.setInstrument(InstrumentEnum.PIANO);
//        tracks.add(track);
//
//        bars = new ArrayList<>();
//        track.setBars(bars);
//
//        bar = new Bar();
//        bars.add(bar);
//
//        notes = new ArrayList<>();
//        bar.setNotes(notes);
//
//        notes.add(new Note(NoteEnum.FA, NoteDurationEnum.N_P));
//        notes.add(new Note(NoteEnum.DO, NoteDurationEnum.N_P));

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
        track.setInstrument(InstrumentEnum.PIANO);
        tracks.add(track);

        List<Bar> bars = new ArrayList<>();
        track.setBars(bars);

        Bar bar = new Bar();
        bars.add(bar);
        bar.setTempo(120);

        List<Note> notes = new ArrayList<>();
        bar.setNotes(notes);

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
//        basic();
        titanic();
    }
}
