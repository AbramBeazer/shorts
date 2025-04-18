package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Accelerock extends Move {

    public Accelerock() {
        super("Accelerock", 40, 100, Type.ROCK, Move.Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }
}
