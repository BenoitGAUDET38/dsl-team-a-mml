package fr.teama;

import fr.teama.grammar.*;
import fr.teama.grammar.MidimlParser;
import fr.teama.structural.Bar;
import fr.teama.structural.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    private Track track;
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

        List<Track> tracks = new ArrayList<>();
        theApp.setTracks(tracks);
        tracks.add(track);

        bars = new ArrayList<>();
        track.setBars(bars);
    }

    @Override public void exitRoot(MidimlParser.RootContext ctx) {
        this.built = true;
    }

    @Override
    public void enterDeclaration(MidimlParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }


    @Override
    public void enterInstrument(MidimlParser.InstrumentContext ctx) {
        this.instrument = ctx.name.getText();
        switch (instrument) {
            case "BATTERIE":
                break;
            default:
                // Bizarre ici non ?
                track = new ClassicTrack();
                track.setInstrument(new Piano());
        }
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
    public void enterChangeTempo(MidimlParser.ChangeTempoContext ctx) {
        currentTempo = Integer.parseInt(ctx.tempo.getText());
    }

    @Override
    public void enterChangeRythme(MidimlParser.ChangeRythmeContext ctx) {
        currentResolution = Integer.parseInt(ctx.rythme.getText().split("/")[0]);
    }

    @Override
    public void enterMesure(MidimlParser.MesureContext ctx) {
        //if track instanceof TrackPiano handle it like piano with note if it is drum handle it like drum with drumnote
        List<Note> notes = new ArrayList<>();
        Bar bar = new Bar(currentTempo, currentResolution, notes);
        bars.add(bar);

        MidimlParser.NoteChaineContext noteChaineContext = ctx.noteChaine();
        while (noteChaineContext != null){
            Note note = new Note();
            if (this.instrument.equals("BATTERIE")){
                note.setNote(NoteEnumDrum.valueOf(noteChaineContext.note.getText()));
            } else {
                note.setNote(NoteEnum.valueOf(noteChaineContext.note.getText()));
            }
            note.setDuration(NoteDurationEnum.valueOf(noteChaineContext.duree.getText()));
            notes.add(note);
            noteChaineContext = noteChaineContext.noteChaine();
        }
        System.out.println(bar);
    }


}

