package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class EarthPower extends Move implements GetsSheerForceBoost {

    public EarthPower() {
        super("Earth Power", 90, 100, Type.GROUND, Category.SPECIAL, Range.NORMAL, 16, false, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPDEF) && !target.isBehindSub()) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, -1, StatEnum.SPDEF);
    }
}
