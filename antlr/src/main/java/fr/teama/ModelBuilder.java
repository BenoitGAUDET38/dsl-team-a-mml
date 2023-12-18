package fr.teama;

import fr.teama.structural.*;
import fr.teama.grammar.*;
import fr.teama.grammar.MidimlParser;

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


    private Track track;
    private List<Note> notes  = new ArrayList<>();



    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(MidimlParser.RootContext ctx) {
        built = false;
        theApp = new App();
        this.track=new Track();
    }

    @Override public void exitRoot(MidimlParser.RootContext ctx) {
        this.track.setNotes(this.notes);
        List<Track> tracks= new ArrayList<>();
        tracks.add(this.track);
        this.theApp.setTracks(tracks);
        this.built = true;
    }

    @Override
    public void enterDeclaration(MidimlParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }


    @Override
    public void enterInstrument(MidimlParser.InstrumentContext ctx) {
        this.instrument = ctx.name.getText();
        track.setInstrument(InstrumentEnum.valueOf(this.instrument));
    }


    @Override
    public void enterGlobalTempo(MidimlParser.GlobalTempoContext ctx) {
        this.theApp.setTempo(Integer.parseInt(ctx.tempo.getText()));
    }

    @Override
    public void enterGlobalRythme(MidimlParser.GlobalRythmeContext ctx) {
        int resolution = Integer.parseInt(ctx.rythme.getText().split("/")[0]);
        this.theApp.setResolution(resolution);
    }

    @Override
    public void enterMesure(MidimlParser.MesureContext ctx) {
        //if track instanceof TrackPiano handle it like piano with note if it is drum handle it like drum with drumnote
        MidimlParser.NoteChaineContext noteChaineContext = ctx.noteChaine();
        while (noteChaineContext != null){
            Note note = new Note();
            note.setNote(NoteEnum.valueOf(noteChaineContext.note.getText()));
            note.setDuration(NoteDurationEnum.valueOf(noteChaineContext.duree.getText()));
            this.notes.add(note);
            System.out.println(note.getNote());
            System.out.println(note.getDuration());
            noteChaineContext = noteChaineContext.noteChaine();
        }
    }


}

