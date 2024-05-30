package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class PsychoCut extends Move implements SlicingMove, HighCritChanceMove {

    public PsychoCut() {
        super("Psycho Cut", 70, 100, Type.PSYCHIC, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, false, 0);
    }
}

