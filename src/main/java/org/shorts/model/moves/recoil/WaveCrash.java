package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class WaveCrash extends DamageDealtPhysicalRecoilAttack {

    public WaveCrash() {
        super("Wave Crash", 120, 100, Type.WATER, 16, true, 0, 1 / 3d);
    }
}

