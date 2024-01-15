package fr.teama;

import fr.teama.exceptions.InvalidTickException;
import fr.teama.grammar.*;
import fr.teama.grammar.MidimlParser;
import fr.teama.structural.*;
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


    private List<Track> tracks;
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
        tracks = new ArrayList<>();
        reusableBars = new HashMap<>();
    }
    @Override
    public void exitRoot(MidimlParser.RootContext ctx) {
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
            case "GUITARE ELECTRIQUE DISTORSION":
                track.setInstrument(InstrumentEnum.ELECTRIC_GUITAR_OVERDRIVE);
                break;
            case "CONTREBASSE":
                track.setInstrument(InstrumentEnum.BASS);
                break;
            case "GUITARE BASSE MEDIATOR":
                track.setInstrument(InstrumentEnum.ELECTRIC_BASS_PICKED);
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
        NormalBar bar = new NormalBar(currentTempo, currentResolution);
        if (ctx.name != null) {
            bar.setName(ctx.name.getText());
        }
        System.out.println("\n\nbegining: " + bars);

        MidimlParser.NoteChaineContext noteChaineContext = ctx.noteChaine();

        while (noteChaineContext != null){
            NoteNumber noteNumber;
            int octaveToAdd = 0;
            switch (instrument) {
                case "BATTERIE":
                    noteNumber = DrumNoteEnum.valueOf(noteChaineContext.noteSimple().note.getText());
                    break;
                default:
                    noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.noteSimple().note.getText());
                    if (noteNumber.getNoteNumber() != -1 && noteChaineContext.noteSimple().octave != null) {
                        octaveToAdd = (Integer.parseInt(noteChaineContext.noteSimple().octave.getText()));
                    }
                    break;
            }
            // Default value with NOIRE
            NoteDurationEnum noteDuration;
            if (noteChaineContext.noteSimple().duree == null) {
                noteDuration = NoteDurationEnum.N;
            } else {
                noteDuration =  NoteDurationEnum.valueOf(noteChaineContext.noteSimple().duree.getText());
            }

            Note note;
            if (noteChaineContext.noteSimple().timing !=null){
                String timingChaine = noteChaineContext.noteSimple().timing.getText();
                // Parse the timing (int or double) to double
                double timing = Double.parseDouble(timingChaine);
                try {
                    note = new Note(noteNumber, noteDuration, timing);
                } catch (InvalidTickException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                note = new Note(noteNumber, noteDuration);
            }

            if (octaveToAdd != 0) {
                note.setOctave(octaveToAdd);
            }

            bar.addNote(note);
            noteChaineContext = noteChaineContext.noteChaine();
        }

//        // Checking if we are reusing a previous bar, meaning that the 'while' did not loop once
//        if (ctx.reused != null) {
//            try {
//                System.out.println("\n\n===================================================\n\n");
//                bar = (ReusedBar) reusableBars.get(ctx.reused.getText()).clone();
//            } catch (Exception e) {
//                System.out.println("Cannot clone bar: " + e.getMessage());
//            }
//
//            System.out.println("\n\nreusedbar: " + bar);
//
//            // Checking if there is any manipulation to do: add, replace, delete
//            if (ctx.manipulation() != null) {
//                // add to a specific position starting from 1 for the musician
//                if (ctx.manipulation().ajout() != null) {
//                    int position = Integer.parseInt(ctx.manipulation().ajout().position.getText()) - 1;
//                    noteChaineContext = ctx.manipulation().ajout().noteChaine();
//                    while (noteChaineContext != null ) {
//                        int noteNumber;
//                        switch (instrument) {
//                            case "BATTERIE":
//                                noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
//                                break;
//                            default:
//                                noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
//                                break;
//                        }
//                        NoteDurationEnum noteDuration = NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
//                        Note note = new Note(noteNumber, noteDuration);
//                        bar.addNote(position, note);
//                        position++;
//                        noteChaineContext = noteChaineContext.noteChaine();
//                    }
//                }
//
//
//                // replace a note by another or a chain of notes
//                else if (ctx.manipulation().remplacement() != null) {
//                    int position = Integer.parseInt(ctx.manipulation().remplacement().position.getText()) - 1;
//                    noteChaineContext = ctx.manipulation().remplacement().noteChaine();
//                    while (noteChaineContext != null ) {
//                        int noteNumber;
//                        switch (instrument) {
//                            case "BATTERIE":
//                                noteNumber = DrumNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
//                                break;
//                            default:
//                                noteNumber = ClassicNoteEnum.valueOf(noteChaineContext.note.getText()).getNoteNumber();
//                                break;
//                        }
//                        NoteDurationEnum noteDuration = NoteDurationEnum.valueOf(noteChaineContext.duree.getText());
//                        Note note = new Note(noteNumber, noteDuration);
//                        System.out.println("\n\nBARS BEFORE MODIFY : " + bars);
//                        bar.modifyNote(position, note);
//                        System.out.println("\n\nBARS AFTER MODIFY : " + bars);
//                        System.out.println("\n\nPOSITION: " + position);
//                        position++;
//                        noteChaineContext = noteChaineContext.noteChaine();
//                    }
//                }
//                // remove a note or a chain of notes
//                else if (ctx.manipulation().suppression() != null) {
//                    int start = Integer.parseInt(ctx.manipulation().suppression().debut.getText());
//                    String end = ctx.manipulation().suppression().fin.getText();
//                    if (end == null) {
//                        bar.removeNote(start, Optional.empty());
//                    } else {
//                        bar.removeNote(start, Optional.of(Integer.parseInt(end)));
//                    }
//                }
//            }
//        }
//
//        // Checking if the bar has a name, which implies that it might be reused
//        if (ctx.name != null) {
//            System.out.println("\n\ngetting the bar name: " + ctx.name.getText());
//            bar.setName(ctx.name.getText());
//            reusableBars.put(ctx.name.getText(), bar);
//        }

        bars.add(bar);
        System.out.println("\n\nfin: " + bars);
    }


}

