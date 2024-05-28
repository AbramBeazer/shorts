package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class ToxicSpikes extends Move implements EntryHazardSetter {

    public ToxicSpikes() {
        super("Toxic Spikes", 0, -1, Type.POISON, Category.STATUS, Range.OTHER_SIDE, 32, false, 100);
    }
}
