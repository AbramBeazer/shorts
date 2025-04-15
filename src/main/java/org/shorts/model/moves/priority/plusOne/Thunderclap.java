package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Thunderclap extends Move {

    public Thunderclap() {
        super("Thunderclap", 70, 100, Type.ELECTRIC, Category.SPECIAL, Range.NORMAL, 8, false, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }

    //TODO: This is like Sucker Punch. Figure out how to implement.
}
