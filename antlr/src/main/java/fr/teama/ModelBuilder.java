package fr.teama;

import fr.teama.grammar.*;
import fr.teama.grammar.MidimlParser;
import fr.teama.structural.Bar;
import fr.teama.structural.Note;
import fr.teama.structural.Track;
import fr.teama.structural.enums.ClassicNoteEnum;
import fr.teama.structural.enums.DrumNoteEnum;
import fr.teama.structural.enums.InstrumentEnum;
import fr.teama.structural.enums.NoteDurationEnum;

import java.util.ArrayList;
import java.util.List;

public class ModelBuilder extends MidimlBaseListener {

    /********************
     ** Business Logic **
     ********************/

    private App theApp = null;
    private boolean built = false;
    private String instrument;


    public App retrieve() {
        if (built) { return theApp; }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    /*******************
     ** Symbol tables **
     *******************/


    private List<Track> tracks;
    private List<Bar> bars;
    private int currentTempo = 120;
    private int currentResolution = 4;



    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(MidimlParser.RootContext ctx) {
        built = false;
        theApp = new App();
        tracks = new ArrayList<>();
    }

    @Override public void exitRoot(MidimlParser.RootContext ctx) {
        theApp.setTracks(tracks);
        this.built = true;
    }

    @Override
    public void enterTitle(MidimlParser.TitleContext ctx) {
        theApp.setName(ctx.name.getText());
    }

    @Override
    public void enterInitialTempo(MidimlParser.InitialTempoContext ctx) {
        currentTempo = Integer.parseInt(ctx.tempo.getText());
    }

    @Override
    public void enterGlobalRythme(MidimlParser.GlobalRythmeContext ctx) {
        currentResolution = Integer.parseInt(ctx.rythme.getText().split("/")[0]);
    }

    @Override
    public void enterInstrument(MidimlParser.InstrumentContext ctx) {
        this.instrument = ctx.name.getText();
        Track track = new Track();
        tracks.add(track);
        bars = new ArrayList<>();
        track.setBars(bars);
        switch (instrument) {
            case "BATTERIE":
                track.setInstrument(InstrumentEnum.DRUM);
                break;
            case "PIANO":
                track.setInstrument(InstrumentEnum.PIANO);
                break;
            case "XYLOPHONE":
                track.setInstrument(InstrumentEnum.XYLOPHONE);
                break;
            case "ACCORDEON":
                track.setInstrument(InstrumentEnum.ACCORDION);
                break;
            case "HARMONICA":
                track.setInstrument(InstrumentEnum.HARMONICA);
                break;
            case "GUITARE":
                track.setInstrument(InstrumentEnum.GUITAR);
                break;
            case "CONTREBASSE":
                track.setInstrument(InstrumentEnum.BASS);
                break;
            case "VIOLON":
                track.setInstrument(InstrumentEnum.VIOLIN);
                break;
            case "TROMPETTE":
                track.setInstrument(InstrumentEnum.TRUMPET);
                break;
            case "TROMBONE":
                track.setInstrument(InstrumentEnum.TROMBONE);
                break;
            case "ALTO":
                track.setInstrument(InstrumentEnum.ALTO_SAX);
                break;
            case "CLARINETTE":
                track.setInstrument(InstrumentEnum.CLARINET);
                break;
            case "FLUTE":
                track.setInstrument(InstrumentEnum.FLUTE);
                break;
            case "WHISTLE":
                track.setInstrument(InstrumentEnum.WHISTLE);
                break;
            case "OCARINA":
                track.setInstrument(InstrumentEnum.OCARINA);
                break;
            case "BANJO":
                track.setInstrument(InstrumentEnum.BANJO);
                break;
            default:
                throw new RuntimeException("Instrument not supported");
        }
        System.out.println(track.getInstrument());
    }

    @Override
    public void enterVolume(MidimlParser.VolumeContext ctx) {
        tracks.get(tracks.size() - 1).setVolume(Integer.parseInt(ctx.volumeVal.getText()));
    }

    @Override
    public void enterChangeTempo(MidimlParser.ChangeTempoContext ctx) {
        currentTempo = Integer.parseInt(ctx.tempo.getText());
    }

    @Override
    public void enterChangeRythme(MidimlParser.ChangeRythmeContext ctx) {
        currentResolution = Integer.parseInt(ctx.rythme.getText().split("/")[0]);
    }

    @Override
    public void enterBar(MidimlParser.BarContext ctx) {
        //if track instanceof TrackPiano handle it like piano with note if it is drum handle it like drum with drumnote
        Bar bar = new Bar(currentTempo, currentResolution);
        bars.add(bar);

        MidimlParser.NoteChaineContext noteChaineContext = ctx.noteChaine();
        while (noteChaineContext != null){
            int noteNumber;
            int octaveToAdd = 0;
            switch (instrument) {
                case "BATTERIE":
                    noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                    break;
                default:
                    noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                    if (noteNumber != -1 && noteChaineContext.octave != null) {
                        octaveToAdd = (Integer.parseInt(noteChaineContext.octave.getText()) - 3) * 12;
                    }
                    break;
            }
            NoteDurationEnum noteDuration =  NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
            Note note = new Note(noteNumber + octaveToAdd, noteDuration);
            bar.addNote(note);
            noteChaineContext = noteChaineContext.noteChaine();
        }
        System.out.println(bar);
    }


}

