package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Psyshock extends SpecialMove {

    public Psyshock() {
        super("Psyshock", 80, 100, Type.PSYCHIC, 16, false, 0);
    }

    @Override
    protected int getDefendingStat(Pokemon user, Pokemon target) {
        return target.calculateDefense();
    }
}
