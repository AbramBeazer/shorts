package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Dive extends Move implements MultiTurnMove {

    //TODO: Verify that power, contact, and PP are correct.
    public Dive() {
        super("Dive", 80, 100, Type.WATER, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Implement
    }

    //TODO: If user is holding Power Herb, the item is consumed and Dig is executed in one turn.

    //TODO: implement the semi-invulnerable turn

    //TODO: (should only decrement PP on second turn) -- maybe we check with multiTurnMove -- we need to encapsulate the decrementPP/pressureApplies stuff as a "usePP" method.
}
