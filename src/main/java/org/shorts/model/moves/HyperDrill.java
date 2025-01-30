package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class HyperDrill extends Move implements GoesThroughProtect {

    public HyperDrill() {
        super("Hyper Drill", 100, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, true, 0);
    }
}
