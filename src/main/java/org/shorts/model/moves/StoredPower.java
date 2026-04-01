package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StoredPower extends Move implements PowerVaries {

    protected static final int MAX_BOOSTS = 42;
    protected static final int BASE_POWER = 20;

    public StoredPower() {
        super("Stored Power", BASE_POWER, 100, Type.PSYCHIC, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        final int boosts = Math.max(0, user.getStageAttack())
            + Math.max(0, user.getStageDefense())
            + Math.max(0, user.getStageSpecialAttack())
            + Math.max(0, user.getStageSpecialDefense())
            + Math.max(0, user.getStageSpeed())
            + Math.max(0, user.getStageEvasion())
            + Math.max(0, user.getStageAccuracy());
        return BASE_POWER * (1 + (double) Math.min(MAX_BOOSTS, boosts));
    }
}
