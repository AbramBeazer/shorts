package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class BleakwindStorm extends Move implements WindMove{
    public BleakwindStorm() {
        super("Bleakwind Storm", 100, 80, Type.FLYING, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPEED) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.SPEED);
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getWeather() == Weather.RAIN || battle.getWeather() == Weather.EXTREME_RAIN) {
            return true;
        }
        return super.rollToHit(user, target, battle);
    }
}
