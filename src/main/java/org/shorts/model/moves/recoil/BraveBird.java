package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class BraveBird extends DamageDealtPhysicalRecoilAttack {

    public BraveBird() {
        super("Brave Bird", 120, 100, Type.FLYING, 24, true, 0, 1 / 3d);
    }
}
