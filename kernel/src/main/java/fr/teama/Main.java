package fr.teama;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.generator.ToSinging;
import fr.teama.structural.*;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.InstrumentEnum;
import fr.teama.structural.enums.NoteDurationEnum;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidTickException {
//        basic();
        simpleTest();
    }

    public static void simpleTest() throws InvalidTickException {
        App app = new App();
        app.setName("test_track");

        List<Track> tracks = new ArrayList<>();
        Track track = new Track();
        List<Bar> bars = new ArrayList<>();

        track.setInstrument(InstrumentEnum.PIANO);
        track.setBars(bars);
        tracks.add(track);
        app.setTracks(tracks);

        NormalBar bar = new NormalBar(120, 4,4);
        bars.add(bar);

        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 0));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 4));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 8));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 12));

        bar = new NormalBar(120, 4,4);
        bars.add(bar);

        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 0));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 4));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 8));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 12));

        bar = new NormalBar(120, 4,4);
        bars.add(bar);

        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 0));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 4));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 8));
        bar.addNote(new Note(ClassicNoteEnum.DO, NoteDurationEnum.N, 12));


        app.accept(new ToSinging());
    }

//    public static void simpleTest() throws InvalidTickException {
//        App app = new App();
//        app.setName("test_track");
//
//        List<Track> tracks = new ArrayList<>();
//        Track track = new Track();
//        List<Bar> bars = new ArrayList<>();
//
//        track.setInstrument(InstrumentEnum.PIANO);
//        track.setBars(bars);
//        tracks.add(track);
//        app.setTracks(tracks);
//
//        Bar bar = new Bar(120, 4);
//        bars.add(bar);
//
//        // Partition de test du Titanic
//        // Ligne 1
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N_P, 0));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.C, 1.5));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 2));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 3));
//
//        bar = new Bar(120, 4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N_P, 0));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.C, 1.5));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 2));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 3));
//
//        bar = new Bar(120, 4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N_P, 0));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.C, 1.5));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 2));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 3));
//        bar = new Bar(120, 4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N_P, 0));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.C, 1.5));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 2));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 3));
//        bar = new Bar(120, 4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N_P, 0));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.C, 1.5));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 2));
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.N, 3));
//
//        app.accept(new ToWiring());
//    }

//    public static void titanic() {
//        App app = new App();
//        app.setName("titanic_piano_simple_lent");
//
//        List<Track> tracks = new ArrayList<>();
//        Track track = new Track();
//        List<Bar> bars = new ArrayList<>();
//
//        track.setInstrument(InstrumentEnum.PIANO);
//        track.setBars(bars);
//        tracks.add(track);
//        app.setTracks(tracks);
//
//        Bar bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//
//        // Partition de test du Titanic
//        // Ligne 1
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.SOL.getNoteNumber(), NoteDurationEnum.N));
//
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.LA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.SOL.getNoteNumber(), NoteDurationEnum.BL));
//
//        // Ligne 2
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bars.add(bar);
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.R));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.SILENCE.getNoteNumber(), NoteDurationEnum.R));
//
//        // Ligne 3
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.SOL.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.LA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.SOL.getNoteNumber(), NoteDurationEnum.BL));
//
//        // Ligne 4
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N_P));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.C));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.FA.getNoteNumber(), NoteDurationEnum.N));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.DO.getNoteNumber(), NoteDurationEnum.R));
//
//        bar = new Bar();
//        bar.setTempo(120);
//        bar.setResolution(4);
//        bars.add(bar);
//        bar.addNote(new Note(ClassicNoteEnum.SILENCE.getNoteNumber(), NoteDurationEnum.BL));
//        bar.addNote(new Note(ClassicNoteEnum.RE.getNoteNumber(), NoteDurationEnum.N));
//        bar.addNote(new Note(ClassicNoteEnum.MI.getNoteNumber(), NoteDurationEnum.N));
//
//        app.accept(new ToWiring());
//    }

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
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.RE);
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.MI);
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.FA.getNoteNumber());
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.SOL);
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.LA);
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);
        note = new Note();
        note.setNote(ClassicNoteEnum.SI);
        note.setDuration(NoteDurationEnum.N);
        bar.addNote(note);

        app.accept(new ToWiring());
    }

    }*/

}
