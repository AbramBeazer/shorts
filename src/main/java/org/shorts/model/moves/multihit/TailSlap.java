package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class TailSlap extends MultiHitMove {

    public TailSlap() {
        super("Tail Slap", 25, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, true, 0, 2, 5);
    }
}
