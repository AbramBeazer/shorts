package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SurgingStrikes extends FixedMultiHitMove {

    public SurgingStrikes() {
        super("Surging Strikes", 25, 100, Type.WATER, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 0, 2);
    }
}
