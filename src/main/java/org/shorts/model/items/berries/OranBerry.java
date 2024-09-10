package org.shorts.model.items.berries;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class OranBerry extends Berry {

    private OranBerry() {
        super("Oran", .5);
    }

    public static final OranBerry ORAN_BERRY = new OranBerry();

    @Override
    public boolean canDoEffect(Pokemon user) {
        return user.getCurrentHP() < user.getMaxHP() && !user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED);
    }

    @Override
    public void doEffect(Pokemon user) {
        user.heal(10 * (user.getAbility() == RIPEN ? 2 : 1));
    }
}
