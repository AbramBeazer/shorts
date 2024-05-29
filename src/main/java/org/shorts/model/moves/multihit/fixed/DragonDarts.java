package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DragonDarts extends FixedMultiHitMove {

    public DragonDarts() {
        super("Dragon Darts", 50, 100, Type.DRAGON, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0, 2);
    }

    //TODO: Do I need to override some getTargets method? This is gonna be wack. I might need to override doMove entirely.
}
