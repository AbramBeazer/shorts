package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.MINIMIZED;

public class BodySlam extends Move implements HitsMinimize, GetsSheerForceBoost {

    public BodySlam() {
        super("Body Slam", 85, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.PARALYZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.PARALYZE);
    }

    @Override
    public boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        return target.hasVolatileStatus(MINIMIZED) || super.rollToHit(user, target, battle);
    }
}
