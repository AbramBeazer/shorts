package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FurySwipes extends MultiHitMove {

    public FurySwipes() {
        super("Fury Swipes", 18, 80, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 24, true, 0, 2, 5);
    }
}
