package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SuckerPunch extends Move {

    public SuckerPunch() {
        super("Sucker Punch", 70, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }

    //TODO: Figure out how to implement
}
