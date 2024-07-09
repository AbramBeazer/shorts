package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class SitrusBerry extends Berry {

    private SitrusBerry() {
        super("Sitrus", .5);
    }

    @Override
    public boolean tryEatingBerry(Pokemon user, Battle battle) {
        if (!user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
            return super.tryEatingBerry(user, battle);
        }
        return false;
    }

    @Override
    public void doEffect(Pokemon user) {
        user.heal(user.getMaxHP() / (user.getAbility() == RIPEN ? 2 : 4));
    }
}
