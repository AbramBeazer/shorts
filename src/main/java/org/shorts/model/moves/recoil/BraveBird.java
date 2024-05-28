package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BraveBird extends RecoilAttack {

    public BraveBird() {
        super("Brave Bird", 120, 100, Type.FLYING, Category.PHYSICAL, Range.SINGLE_ANY, 24, true, 0, 1 / 3d);
    }
}
