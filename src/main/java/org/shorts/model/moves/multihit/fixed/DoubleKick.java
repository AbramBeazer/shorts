package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DoubleKick extends FixedMultiHitMove {

    public DoubleKick() {
        super("Double Kick", 30, 100, Type.FIGHTING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 48, true, 0, 2);
    }
}
