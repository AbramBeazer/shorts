package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.AlwaysCritMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SurgingStrikes extends FixedMultiHitMove implements AlwaysCritMove {

    public SurgingStrikes() {
        super("Surging Strikes", 25, 100, Type.WATER, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, true, 0, 2);
    }
}
