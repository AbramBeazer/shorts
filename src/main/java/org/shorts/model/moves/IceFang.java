package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.FREEZE;
import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class IceFang extends Move implements BitingMove, GetsSheerForceBoost {

    private boolean calculatingFreeze;

    public IceFang() {
        super("Ice Fang", 65, 95, Type.ICE, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        calculatingFreeze = true;
        if (FREEZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }

        calculatingFreeze = false;
        if (FLINCH.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (calculatingFreeze) {
            target.setStatus(Status.FREEZE);
        } else {
            target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
        }
    }
}
