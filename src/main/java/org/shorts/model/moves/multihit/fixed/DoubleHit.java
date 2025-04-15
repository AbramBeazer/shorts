package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DoubleHit extends FixedMultiHitMove {

    public DoubleHit() {
        super("Double Hit", 35, 90, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, true, 0, 2);
    }
}
