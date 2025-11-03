package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.CONFUSED;

public class WaterPulse extends Move implements PulseMove, GetsSheerForceBoost {

    public WaterPulse() {
        super("Water Pulse", 60, 100, Type.WATER, Category.SPECIAL, Range.SINGLE_ANY, 32, false, 20);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (CONFUSED.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(VolatileStatus.createConfusion());
    }
}
