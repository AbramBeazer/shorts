package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class AquaCutter extends Move implements SlicingMove, HighCritChanceMove {

    public AquaCutter() {
        super("Aqua Cutter", 70, 100, Type.WATER, Category.PHYSICAL, Range.NORMAL, 32, false, 0);
    }
}
