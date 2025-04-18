package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Psyshock extends Move {

    public Psyshock() {
        super("Psyshock", 80, 100, Type.PSYCHIC, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected double getDefendingStat(Pokemon user, Pokemon target, Battle battle) {
        return target.calculateDefense(battle);
    }
}
