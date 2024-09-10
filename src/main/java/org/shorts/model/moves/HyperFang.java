package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class HyperFang extends Move implements BitingMove, GetsSheerForceBoost {

    public HyperFang() {
        super("Hyper Fang", 80, 90, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (FLINCH.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
    }

    //Not in Gen 9
}
