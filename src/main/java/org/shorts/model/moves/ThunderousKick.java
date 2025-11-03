package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class ThunderousKick extends Move implements KickingMove, GetsSheerForceBoost{

    public ThunderousKick() {
        super("Thunderous Kick", 90, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.DEF) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.DEF);
    }
}
