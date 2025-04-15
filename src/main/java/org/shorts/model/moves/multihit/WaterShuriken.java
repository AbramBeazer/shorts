package org.shorts.model.moves.multihit;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class WaterShuriken extends MultiHitMove {

    public WaterShuriken() {
        super("Water Shuriken", 15, 100, Type.WATER, Category.SPECIAL, Range.NORMAL, 32, false, 0, 2, 5);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 1;
    }
}
