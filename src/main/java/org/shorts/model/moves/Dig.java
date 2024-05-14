package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Dig extends PhysicalMove implements MultiTurnMove {

    public Dig() {
        super("Dig", 80, 100, Type.GROUND, 16, false, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: Implement
    }

    //TODO: If user is holding Power Herb, the item is consumed and Dig is executed in one turn.

    //TODO: implement the semi-invulnerable turn

    //TODO: (should only decrement PP on second turn) -- maybe we check with multiTurnMove -- we need to encapsulate the decrementPP/pressureApplies stuff as a "usePP" method.
}
