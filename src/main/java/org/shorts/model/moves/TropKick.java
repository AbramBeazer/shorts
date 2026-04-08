package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class TropKick extends Move implements KickingMove, GetsSheerForceBoost {

    public TropKick() {
        super("Trop Kick", 85, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 24, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.ATK) && !target.isBehindSub()) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, -1, StatEnum.ATK);
    }
}
