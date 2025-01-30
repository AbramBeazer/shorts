package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class QuickAttack extends Move {

    public QuickAttack() {
        super("Quick Attack", 40, 100, Type.NORMAL, Move.Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 48, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }
}
