package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class CloseCombat extends Move {

    public CloseCombat() {
        super("Close Combat", 120, 100, Type.FIGHTING, MoveGroup.PHYSICAL, 8, true);
    }

    @Override
    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
        attacker.changeDefense(-1);
        attacker.changeSpecialDefense(-1);
    }
}
