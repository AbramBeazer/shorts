package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class CrossChop extends Move implements HighCritChanceMove {

    public CrossChop() {
        super("Cross Chop", 100, 80, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, true, 0);
    }
}
