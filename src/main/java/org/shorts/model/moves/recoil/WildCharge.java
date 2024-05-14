package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class WildCharge extends DamageDealtPhysicalRecoilAttack {

    public WildCharge() {
        super("Wild Charge", 90, 100, Type.ELECTRIC, 24, true, 0, 0.25);
    }
}