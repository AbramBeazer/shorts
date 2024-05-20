package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class Facade extends PhysicalMove {

    static final double MULTIPLIER = 2;

    public Facade() {
        super("Facade", 70, 100, Type.NORMAL, 32, true, 0);
    }

    @Override
    protected double getBurnMultiplier(Pokemon user) {
        return 1;
    }

    @Override
    protected double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        if (user.getStatus() == Status.BURN || user.getStatus() == Status.PARALYZE || user.getStatus() == Status.POISON
            || user.getStatus() == Status.TOXIC_POISON) {
            return MULTIPLIER * super.calculateMovePower(user, target, battle);
        }
        return super.calculateMovePower(user, target, battle);
    }
}
