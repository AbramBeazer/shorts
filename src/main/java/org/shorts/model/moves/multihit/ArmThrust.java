package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class ArmThrust extends MultiHitMove {

    public ArmThrust() {
        super("Arm Thrust", 15, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 0, 2, 5);
    }
}