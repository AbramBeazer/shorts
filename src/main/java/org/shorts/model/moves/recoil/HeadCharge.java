package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class HeadCharge extends RecoilAttack {

    public HeadCharge() {
        super("Head Charge", 120, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 0, 0.25);
    }
}
