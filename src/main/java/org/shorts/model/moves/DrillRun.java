package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class DrillRun extends Move implements HighCritChanceMove {

    public DrillRun() {
        super("Drill Run", 80, 95, Type.GROUND, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }
}
