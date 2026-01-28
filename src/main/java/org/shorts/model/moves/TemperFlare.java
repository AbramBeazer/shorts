package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TemperFlare extends Move {

    public static final int MULTIPLIER = 2;

    public TemperFlare() {
        super("Temper Flare", 75, 100, Type.FIRE, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        return user.isLastMoveFailed() ? MULTIPLIER : 1;
    }
}
