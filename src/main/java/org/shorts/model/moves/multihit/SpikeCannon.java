package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SpikeCannon extends MultiHitMove {

    public SpikeCannon() {
        super("Spike Cannon", 20, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, false, 0, 2, 5);
    }
}
