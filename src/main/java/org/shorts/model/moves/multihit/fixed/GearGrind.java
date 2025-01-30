package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class GearGrind extends FixedMultiHitMove {

    public GearGrind() {
        super("Gear Grind", 50, 85, Type.STEEL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 0, 2);
    }
}
