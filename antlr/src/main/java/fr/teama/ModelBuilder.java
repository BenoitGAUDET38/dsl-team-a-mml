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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String, Bar> reusableBars;
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
        track = new Track();
        tracks.add(track);

        bars = new ArrayList<>();
        track.setBars(bars);
        
        reusableBars = new HashMap<>();
    }

    @Override
    public void exitRoot(MidimlParser.RootContext ctx) {
        this.built = true;
    }

    @Override
    public void enterTitle(MidimlParser.TitleContext ctx) {
        theApp.setName(ctx.name.getText());
    }


    @Override
    public void enterInstrument(MidimlParser.InstrumentContext ctx) {
        this.instrument = ctx.name.getText();
        switch (instrument) {
            case "BATTERIE":
                track.setInstrument(InstrumentEnum.DRUM);
                break;
            case "PIANO":
                track.setInstrument(InstrumentEnum.PIANO);
                break;
            default:
                throw new RuntimeException("Instrument not supported");
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
    public void enterBar(MidimlParser.BarContext ctx) {
        //if track instanceof TrackPiano handle it like piano with note if it is drum handle it like drum with drumnote
        Bar bar = new Bar(currentTempo, currentResolution);
        System.out.println("\n\nbegining: " + bars);

        MidimlParser.NoteChaineContext noteChaineContext = ctx.noteChaine();
        
        while (noteChaineContext != null){
            int noteNumber;
            switch (instrument) {
                case "BATTERIE":
                    noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                    break;
                default:
                    noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                    break;
            }
            NoteDurationEnum noteDuration =  NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
            Note note = new Note(noteNumber, noteDuration);
            bar.addNote(note);
            noteChaineContext = noteChaineContext.noteChaine();
        }

        // Checking if we are reusing a previous bar, meaning that the 'while' did not loop once
        if (ctx.reused != null) {
            try {
                System.out.println("\n\n===================================================\n\n");
                bar = (Bar) reusableBars.get(ctx.reused.getText()).clone();
            } catch (Exception e) {
                System.out.println("Cannot clone bar: " + e.getMessage());
            }
            
            System.out.println("\n\nreusedbar: " + bar);

            // Checking if there is any manipulation to do: add, replace, delete
            if (ctx.manipulation() != null) {
                // add to a specific position starting from 1 for the musician
                if (ctx.manipulation().ajout() != null) {
                    int position = Integer.parseInt(ctx.manipulation().ajout().position.getText()) - 1;
                    noteChaineContext = ctx.manipulation().ajout().noteChaine();
                    while (noteChaineContext != null ) {
                        int noteNumber;
                        switch (instrument) {
                            case "BATTERIE":
                                noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                                break;
                            default:
                                noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                                break;
                        }
                        NoteDurationEnum noteDuration = NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
                        Note note = new Note(noteNumber, noteDuration);
                        bar.addNote(position, note);
                        position++;
                        noteChaineContext = noteChaineContext.noteChaine();
                    }  
                }
                // replace a note by another or a chain of notes
                else if (ctx.manipulation().remplacement() != null) {
                    int position = Integer.parseInt(ctx.manipulation().remplacement().position.getText()) - 1;
                    noteChaineContext = ctx.manipulation().remplacement().noteChaine();
                    while (noteChaineContext != null ) {
                        int noteNumber;
                        switch (instrument) {
                            case "BATTERIE":
                                noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                                break;
                            default:
                                noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
                                break;
                        }
                        NoteDurationEnum noteDuration = NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
                        Note note = new Note(noteNumber, noteDuration);
                        System.out.println("\n\nBARS BEFORE MODIFY : " + bars);
                        bar.modifyNote(position, note);
                        System.out.println("\n\nBARS AFTER MODIFY : " + bars);
                        System.out.println("\n\nPOSITION: " + position);
                        position++;
                        noteChaineContext = noteChaineContext.noteChaine();
                    }
                }
                // remove a note or a chain of notes
                else if (ctx.manipulation().suppression() != null) {
                    int start = Integer.parseInt(ctx.manipulation().suppression().debut.getText());
                    String end = ctx.manipulation().suppression().fin.getText();
                    if (end == null) {
                        bar.removeNote(start, Optional.empty());
                    } else {
                        bar.removeNote(start, Optional.of(Integer.parseInt(end)));
                    }
                }
            }
        }

        // Checking if the bar has a name, which implies that it might be reused
        if (ctx.name != null) {
            System.out.println("\n\ngetting the bar name: " + ctx.name.getText());
            bar.setName(ctx.name.getText());
            reusableBars.put(ctx.name.getText(), bar);
        }

        bars.add(bar);
        System.out.println("\n\nfin: " + bars);
    }


}

