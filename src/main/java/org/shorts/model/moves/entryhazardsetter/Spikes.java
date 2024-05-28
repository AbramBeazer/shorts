package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Spikes extends Move implements EntryHazardSetter {

    public Spikes() {
        super("Spikes", 0, -1, Type.GROUND, Category.STATUS, Range.OTHER_SIDE, 32, false, 100);
    }

}
