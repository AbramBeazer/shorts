package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Hurricane extends Move implements CanHitFly, WindMove {

    public Hurricane() {
        super("Hurricane", 110, 70, Type.FLYING, Category.SPECIAL, Range.SINGLE_ANY, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (VolatileStatusType.CONFUSED.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(VolatileStatus.createConfusion());
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getWeather() == Weather.RAIN || battle.getWeather() == Weather.EXTREME_RAIN) {
            return true;
        }
        return super.rollToHit(user, target, battle);
    }

    @Override
    public double getAccuracy(Battle battle) {
        if (battle.getWeather() == Weather.SUN || battle.getWeather() == Weather.EXTREME_SUN) {
            return 50;
        } else {
            return super.getAccuracy(battle);
        }
    }
}
