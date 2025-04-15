package org.shorts.model.moves.priority;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ExtremeSpeed extends Move {

    public ExtremeSpeed() {
        super("Extreme Speed", 80, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 8, true, 0);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 2;
    }
}
