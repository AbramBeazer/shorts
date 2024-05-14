package org.shorts.model.moves.entryhazardsetter;

import org.shorts.model.moves.StatusMove;
import org.shorts.model.types.Type;

public class StealthRock extends StatusMove implements EntryHazardSetter {

    public StealthRock() {
        super("Stealth Rock", 0, Type.ROCK, 32, false);
    }

}
