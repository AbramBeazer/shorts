package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class GrassKnot extends Move {

    public GrassKnot() {
        super("Grass Knot", 0, 100, Type.GRASS, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        final double weight = target.getWeight();
        if (weight < 10) {
            return 20;
        } else if (weight < 25) {
            return 40;
        } else if (weight < 50) {
            return 60;
        } else if (weight < 100) {
            return 80;
        } else if (weight < 200) {
            return 100;
        } else {
            return 120;
        }
    }
}
