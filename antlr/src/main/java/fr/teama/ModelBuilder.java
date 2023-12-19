package fr.teama;

import fr.teama.structural.*;
import fr.teama.grammar.*;
import fr.teama.grammar.MidimlParser;

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
    private Optional<Integer> newTempo = Optional.empty();



    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(MidimlParser.RootContext ctx) {
        built = false;
        theApp = new App();

        List<Track> tracks = new ArrayList<>();
        theApp.setTracks(tracks);
        track = new Track();
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
        track.setInstrument(InstrumentEnum.valueOf(this.instrument));
    }


    @Override
    public void enterInitialTempo(MidimlParser.InitialTempoContext ctx) {
        this.theApp.setTempo(Integer.parseInt(ctx.tempo.getText()));
    }

    @Override
    public void enterGlobalRythme(MidimlParser.GlobalRythmeContext ctx) {
        int resolution = Integer.parseInt(ctx.rythme.getText().split("/")[0]);
        this.theApp.setResolution(resolution);
    }

    @Override
    public void enterChangeTempo(MidimlParser.ChangeTempoContext ctx) {
        newTempo = Optional.of(Integer.parseInt(ctx.tempo.getText()));
    }

    @Override
    public void enterMesure(MidimlParser.MesureContext ctx) {
        //if track instanceof TrackPiano handle it like piano with note if it is drum handle it like drum with drumnote
        Bar bar = new Bar();
        bars.add(bar);
        // Only set the tempo if it was changed in this bar
        if (newTempo.isPresent()) {
            bar.setTempo(newTempo.get());
            newTempo = Optional.empty();
        }
        List<Note> notes = new ArrayList<>();
        bar.setNotes(notes);
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
            System.out.println(note.getNote());
            System.out.println(note.getDuration());
            noteChaineContext = noteChaineContext.noteChaine();
        }
    }


}

