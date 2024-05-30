package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class IceShard extends Move {

    public IceShard() {
        super("Ice Shard", 40, 100, Type.ICE, Move.Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 48, false, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 1;
    }
}
