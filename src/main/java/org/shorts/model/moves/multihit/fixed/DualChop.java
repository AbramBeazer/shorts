package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DualChop extends FixedMultiHitMove {

    public DualChop() {
        super("Dual Chop", 40, 90, Type.DRAGON, Category.PHYSICAL, Range.NORMAL, 24, true, 0, 2);
    }
}
