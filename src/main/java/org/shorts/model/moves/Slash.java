package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Slash extends Move implements SlicingMove, HighCritChanceMove {

    public Slash() {
        super("Slash", 70, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 0);
    }
}
