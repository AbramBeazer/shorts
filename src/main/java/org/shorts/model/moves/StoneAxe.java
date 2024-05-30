package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StoneAxe extends Move implements BoostedBySharpness {

    public StoneAxe() {
        super("Stone Axe", 65, 90, Type.ROCK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        battle.getOpposingTrainer(user).setRocks(true);
    }
}
