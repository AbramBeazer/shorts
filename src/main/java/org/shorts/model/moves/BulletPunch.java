package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BulletPunch extends PhysicalMove {

    public BulletPunch() {
        super("Bullet Punch", 40, 100, Type.STEEL, 48, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 1;
    }
}
