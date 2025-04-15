package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StoredPower extends Move {

    protected static final int MAX_BOOSTS = 42;

    public StoredPower() {
        super("Stored Power", 20, 100, Type.PSYCHIC, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final int boosts = Math.max(0, user.getStageAttack())
            + Math.max(0, user.getStageDefense())
            + Math.max(0, user.getStageSpecialAttack())
            + Math.max(0, user.getStageSpecialDefense())
            + Math.max(0, user.getStageSpeed())
            + Math.max(0, user.getStageEvasion())
            + Math.max(0, user.getStageAccuracy());
        return 1 + (double) Math.min(MAX_BOOSTS, boosts);
    }
}
