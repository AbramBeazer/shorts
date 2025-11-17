package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Infiltrator.*;
import static org.shorts.model.abilities.Soundproof.*;

public class Howl extends Move implements SoundEffect {

    public Howl() {
        super("Howl", 0, -1, Type.NORMAL, Category.STATUS, Range.OWN_PARTY, 64, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (affectsTarget(user, target)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(1, StatEnum.ATK);
    }

    protected boolean affectsTarget(Pokemon user, Pokemon target) {
        boolean canAffect = target.canChangeStat(1, StatEnum.ATK);
        if (user != target) {
            if (target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
                canAffect &=
                    user.getAbility() == INFILTRATOR && !user.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
                        && target.canChangeStat(1, StatEnum.ATK);
            }
            if (target.getAbility() == SOUNDPROOF) {
                canAffect &= target.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
                        || target.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED);
            }
        }

        return canAffect;
    }
}
