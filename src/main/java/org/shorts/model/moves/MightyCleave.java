package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class MightyCleave extends Move implements SlicingMove, GoesThroughProtect {

    public MightyCleave() {
        super("Mighty Cleave", 95, 100, Type.ROCK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 0);
    }
}
