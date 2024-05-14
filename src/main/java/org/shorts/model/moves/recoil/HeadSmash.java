package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class HeadSmash extends DamageDealtPhysicalRecoilAttack {

    public HeadSmash() {
        super("Head Smash", 150, 80, Type.ROCK, 8, true, 0, 0.5);
    }
}
