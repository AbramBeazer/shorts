package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Tailwind extends Move implements WindMove {

    static final int BASE_TURNS = 4;

    public Tailwind() {
        super("Tailwind", 0, -1, Type.FLYING, Category.STATUS, Range.OWN_SIDE, 24, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        battle.getCorrespondingTrainer(user).setTailwindTurns(BASE_TURNS);
    }
}
