package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Fling extends Move {

    public Fling() {
        super("Fling", 0, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return super.getPower(user, target, battle);
    }
}
