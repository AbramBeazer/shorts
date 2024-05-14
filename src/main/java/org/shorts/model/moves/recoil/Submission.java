package org.shorts.model.moves.recoil;

import org.shorts.model.types.Type;

public class Submission extends DamageDealtPhysicalRecoilAttack {

    public Submission() {
        super("Submission", 80, 80, Type.FIGHTING, 32, true, 0, 0.25);
    }
}
