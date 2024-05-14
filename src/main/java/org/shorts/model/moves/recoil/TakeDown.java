package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class TakeDown extends DamageDealtPhysicalRecoilAttack {

    public TakeDown() {
        super("Take Down", 90, 85, Type.NORMAL, 32, true, 0, 0.25);
    }
}