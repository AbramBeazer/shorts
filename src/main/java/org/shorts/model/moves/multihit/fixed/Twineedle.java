package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Twineedle extends FixedMultiHitMove implements GetsSheerForceBoost {

    public Twineedle() {
        super("Twineedle", 25, 100, Type.BUG, Category.PHYSICAL, Range.NORMAL, 32, false, 0, 2);
    }

    //Not in Gen 8 or 9.
}
