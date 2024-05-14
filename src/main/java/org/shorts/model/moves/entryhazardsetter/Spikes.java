package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.StatusMove;
import org.shorts.model.types.Type;

public class Spikes extends StatusMove implements EntryHazardSetter {

    public Spikes() {
        super("Spikes", 0, Type.GROUND, 32, false);
    }

}
