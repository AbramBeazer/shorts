package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FuryAttack extends MultiHitMove {

    public FuryAttack() {
        super("Fury Attack", 15, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 0, 2, 5);
    }
}
