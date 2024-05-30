package org.shorts.model.moves.priority.plusOne;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ShadowSneak extends Move {

    public ShadowSneak() {
        super("Shadow Sneak", 40, 100, Type.GHOST, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 48, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        return 1;
    }
}
