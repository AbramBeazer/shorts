package org.shorts.model.items.berries;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class SitrusBerry extends Berry {

    private SitrusBerry() {
        super("Sitrus", .5);
    }

    public static final SitrusBerry SITRUS_BERRY = new SitrusBerry();

    @Override
    public boolean canDoEffect(Pokemon user) {
        return user.getCurrentHP() < user.getMaxHP() && !user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED);
    }

    @Override
    public void doEffect(Pokemon user) {
        user.heal(user.getMaxHP() / (user.getAbility() == RIPEN ? 2 : 4));
    }
}
