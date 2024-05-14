package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.StatusMove;
import org.shorts.model.types.Type;

public class ToxicSpikes extends StatusMove implements EntryHazardSetter {

    public ToxicSpikes() {
        super("Toxic Spikes", 0, Type.POISON, 32, false);
    }
}
