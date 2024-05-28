package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class TakeDown extends RecoilAttack {

    public TakeDown() {
        super("Take Down", 90, 85, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 0, 0.25);
    }
}