package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.types.Type.FIRE;

public class Scald extends SpecialMove {

    public Scald() {
        super("Scald", 80, 100, Type.WATER, 24, false, 30);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!defender.getTypes().contains(FIRE)) {
            defender.setStatus(Status.BURN);
        }
    }
}
