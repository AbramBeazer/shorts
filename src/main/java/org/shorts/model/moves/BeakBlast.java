package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BeakBlast extends Move implements BallBombMove {

    public BeakBlast() {
        super("Beak Blast", 100, 100, Type.FLYING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, false, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return -3;
    }

    //TODO: Implement -- this is similar to Focus Punch
}
