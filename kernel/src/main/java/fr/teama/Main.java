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

}
