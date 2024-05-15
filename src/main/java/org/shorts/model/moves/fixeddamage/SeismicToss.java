package org.shorts.model.moves.fixeddamage;

import org.shorts.battle.Battle;
import org.shorts.model.moves.PhysicalMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Scrappy.SCRAPPY;

public class SeismicToss extends PhysicalMove {

    public SeismicToss() {
        super("Seismic Toss", 0, 100, Type.FIGHTING, 32, true, 0);
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        if (Type.getTypeMultiplier(this.getType(), target.getTypes()) == 0 && user.getAbility() != SCRAPPY) {
            return 0;
        } else {
            return user.getLevel();
        }
    }
}

