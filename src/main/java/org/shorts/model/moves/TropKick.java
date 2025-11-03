package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class TropKick extends Move implements KickingMove, GetsSheerForceBoost {

    public TropKick() {
        super("Trop Kick", 70, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 24, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.ATK) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.ATK);
    }
}
