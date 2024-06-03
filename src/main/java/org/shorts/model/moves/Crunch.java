package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Crunch extends Move implements BitingMove {

    public Crunch() {
        super("Crunch", 80, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 20);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeDefense(-1);
    }
}
