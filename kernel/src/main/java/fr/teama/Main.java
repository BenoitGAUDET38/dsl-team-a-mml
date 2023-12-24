package fr.teama;

import fr.teama.generator.ToWiring;
import fr.teama.structural.abstracts.Bar;
import fr.teama.structural.abstracts.Note;
import fr.teama.structural.classic.*;
import fr.teama.structural.classic.instruments.Piano;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.NoteDurationEnum;
import fr.teama.structural.abstracts.Track;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void titanic() {
        App app = new App();
        app.setName("titanic_piano_simple_lent");

        List<Track> tracks = new ArrayList<>();
        ClassicTrack track = new ClassicTrack();
        ClassicInstrument piano = new Piano();
        List<Bar> bars = new ArrayList<>();
        List<Note> notes = new ArrayList<>();

        track.setInstrument(piano);
        track.setBars(bars);
        tracks.add(track);
        app.setTracks(tracks);

        Bar bar = new ClassicBar(100, 4, notes);
        bars.add(bar);

        // Partition de test du Titanic
        // Ligne 1
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.N));


        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 2
        notes = new ArrayList<>();
        bar = new ClassicBar(500, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        bars.add(bar);
        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.DO, NoteDurationEnum.R));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(null, NoteDurationEnum.R));

        // Ligne 3
        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.LA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.SOL, NoteDurationEnum.BL));

        // Ligne 4
        bar.setTempo(120); // back to normal
        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes); // might be useless since we set the tempo above
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N_P));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.C));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.FA, NoteDurationEnum.N));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(ClassicNoteEnum.DO, NoteDurationEnum.R));

        notes = new ArrayList<>();
        bar = new ClassicBar(120, 4, notes);
        bars.add(bar);
        notes.add(new ClassicNote(null, NoteDurationEnum.BL));
        notes.add(new ClassicNote(ClassicNoteEnum.RE, NoteDurationEnum.N));
        notes.add(new ClassicNote(ClassicNoteEnum.MI, NoteDurationEnum.N));

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

    /*public static void basic() {
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

        List<Note> notes = new ArrayList<>();
        Bar bar = new Bar(120, 4, notes);
        bars.add(bar);

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
    }

    }*/
    public static void main(String[] args) {
//        basic();
        titanic();
    }
}
