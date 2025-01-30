package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class WildCharge extends RecoilAttack {

    public WildCharge() {
        super("Wild Charge", 90, 100, Type.ELECTRIC, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 0, 0.25);
    }
}