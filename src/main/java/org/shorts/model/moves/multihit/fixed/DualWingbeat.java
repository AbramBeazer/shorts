package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DualWingbeat extends FixedMultiHitMove {

    public DualWingbeat() {
        super("Dual Wingbeat", 40, 90, Type.FLYING, Category.PHYSICAL, Range.NORMAL, 16, true, 0, 2);
    }
}
