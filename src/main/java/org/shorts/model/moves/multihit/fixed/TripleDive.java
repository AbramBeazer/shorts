package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class TripleDive extends FixedMultiHitMove {

    public TripleDive() {
        super("Triple Dive", 30, 95, Type.WATER, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, true, 0, 3);
    }
}
