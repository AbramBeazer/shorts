package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class LightOfRuin extends RecoilAttack {

    //Note that this move doesn't exist from Gen 8 onward.

    public LightOfRuin() {
        super("Light of Ruin", 140, 90, Type.FAIRY, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, false, 0, 0.5);
    }
}
