package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.MegaLauncher.MEGA_LAUNCHER;

public class HealPulse extends Move implements PulseEffect, HealingMove, AffectedByMagicBounce {

    public HealPulse() {
        super("Heal Pulse", 0, -1, Type.PSYCHIC, Category.STATUS, Range.SINGLE_ANY, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE) && !target.isAtFullHP()) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getAbility() == MEGA_LAUNCHER) {
            target.heal(target.getMaxHP() * 3 / 4);
        } else {
            target.heal(target.getMaxHP() / 2);
        }
    }
}
