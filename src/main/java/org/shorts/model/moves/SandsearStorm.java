package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class SandsearStorm extends Move implements WindMove{
    public SandsearStorm() {
        super("Sandsear Storm", 100, 80, Type.GROUND, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 16, false, 20);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getWeather() == Weather.RAIN || battle.getWeather() == Weather.EXTREME_RAIN) {
            return true;
        }
        return super.rollToHit(user, target, battle);
    }
}
