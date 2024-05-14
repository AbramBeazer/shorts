package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class DoubleEdge extends DamageDealtPhysicalRecoilAttack {

    public DoubleEdge() {
        super("Double-Edge", 120, 100, Type.NORMAL, 24, true, 0, 1 / 3d);
    }
}
