package fr.teama.structural.drum;

import fr.teama.structural.abstracts.Instrument;

public class Drum extends Instrument {
    int instrumentChannelNumber;

    public Drum () {
        super();
        this.instrumentChannelNumber = 9;
    }
}
