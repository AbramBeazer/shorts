package org.shorts.model.moves.trapping;

import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SpiritShackle extends TrappingMove implements GetsSheerForceBoost {

    public SpiritShackle() {
        super("Spirit Shackle", 80, 100, Type.GHOST, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }
}
