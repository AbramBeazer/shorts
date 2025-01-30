package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Screech extends Move implements AffectedByMagicBounce, SoundEffect {

    public Screech() {
        super("Screech", 0, 85, Type.NORMAL, Category.STATUS, Range.NORMAL_SINGLE_ADJACENT_ANY, 64, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.DEF) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)
            && !SoundEffect.super.soundproofApplies(target)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-2, StatEnum.DEF);
    }

}
