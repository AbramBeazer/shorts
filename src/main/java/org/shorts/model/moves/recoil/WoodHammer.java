package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class WoodHammer extends DamageDealtPhysicalRecoilAttack {

    public WoodHammer() {
        super("Wood Hammer", 120, 100, Type.GRASS, 24, true, 0, 1 / 3d);
    }
}
