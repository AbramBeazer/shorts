package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class PsychoCut extends Move implements SlicingMove, HighCritChanceMove {

    public PsychoCut() {
        super("Psycho Cut", 70, 100, Type.PSYCHIC, Category.PHYSICAL, Range.NORMAL, 32, false, 0);
    }
}

