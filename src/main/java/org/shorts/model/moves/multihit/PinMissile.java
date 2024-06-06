package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class PinMissile extends MultiHitMove {

    public PinMissile() {
        super("Pin Missile", 25, 95, Type.BUG, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, false, 0, 2, 5);
    }
}
