package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class PowerUpPunch extends Move implements PunchingMove, GetsSheerForceBoost {

    public PowerUpPunch() {
        super("Power-Up Punch", 40, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 32, true, 100);
    }


    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.canChangeStat(1, StatEnum.ATK)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeStat(1, StatEnum.ATK);
    }
}
