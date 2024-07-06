package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DoubleIronBash extends FixedMultiHitMove implements GetsSheerForceBoost {

    public DoubleIronBash() {
        super("Double Iron Bash", 60, 100, Type.STEEL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 0, 2);
    }
}
