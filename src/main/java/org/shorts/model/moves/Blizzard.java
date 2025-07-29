package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Blizzard extends Move implements WindMove {

    public Blizzard() {
        super("Blizzard", 110, 70, Type.ICE, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 8, false, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.FREEZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.FREEZE);
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (battle.getWeather() == Weather.SNOW || battle.getWeather() == Weather.HAIL) {
            return true;
        }
        return super.rollToHit(user, target, battle);
    }
}
