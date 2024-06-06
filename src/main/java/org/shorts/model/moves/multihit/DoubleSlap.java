package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DoubleSlap extends MultiHitMove {

    public DoubleSlap() {
        super("Double Slap", 15, 85, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0, 2, 5);
    }
}
