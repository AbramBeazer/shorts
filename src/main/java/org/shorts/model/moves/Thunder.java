package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Thunder extends SpecialMove {

    public Thunder() {
        super("Thunder", 120, 70, Type.ELECTRIC, 16, false, 30);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!defender.getTypes().contains(Type.ELECTRIC)) {
            defender.setStatus(Status.PARALYZE);
        }
    }

}
