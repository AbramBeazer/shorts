package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class Spore extends Move implements PowderSporeEffect, AffectedByMagicBounce {

    public Spore() {
        super("Spore", 0, -1, Type.GRASS, Category.STATUS, Range.NORMAL, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Maybe the check for substitute and semi-invulnerable should be in the parent method.
        if (target.hasVolatileStatus(SUBSTITUTE) || target.hasVolatileStatus(SEMI_INVULNERABLE)
            || !this.asPowderSporeEffectData().canActivate(target)) {
            System.out.println("It doesn't affect " + target.getNickname());
        } else {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.createSleep());
    }

}
