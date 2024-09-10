package org.shorts.model.moves.healthdependent;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class WaterSpout extends HealthDependentMove {

    public WaterSpout() {
        super("Water Spout", 150, 100, Type.WATER, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 8, false, 0);
    }
}
