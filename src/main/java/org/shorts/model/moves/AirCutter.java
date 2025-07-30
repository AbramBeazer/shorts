package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class AirCutter extends Move implements SlicingMove, HighCritChanceMove, WindMove {

    public AirCutter() {
        super("Air Cutter", 60, 95, Type.FLYING, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 40, false, 0);
    }
}
