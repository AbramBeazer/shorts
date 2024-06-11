package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FishiousRend extends Move implements BitingMove {

    public FishiousRend() {
        super("Fishious Rend", 85, 100, Type.WATER, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        if (target.hasMovedThisTurn()) {
            return 1;
        } else {
            return 2;
        }
    }
}
