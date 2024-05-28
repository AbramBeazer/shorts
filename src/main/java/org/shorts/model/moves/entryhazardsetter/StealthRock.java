package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class StealthRock extends Move implements EntryHazardSetter {

    public StealthRock() {
        super("Stealth Rock", 0, -1, Type.ROCK, Category.STATUS, Range.OTHER_SIDE, 32, false, 100);
    }

}
