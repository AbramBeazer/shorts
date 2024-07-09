package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class OranBerry extends Berry {

    private OranBerry() {
        super("Oran", .5);
    }

    public static final OranBerry ORAN_BERRY = new OranBerry();

    @Override
    public boolean tryEatingBerry(Pokemon user, Battle battle) {
        if (!user.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
            return super.tryEatingBerry(user, battle);
        }
        return false;
    }

    @Override
    public void doEffect(Pokemon user) {
        user.heal(10 * (user.getAbility() == RIPEN ? 2 : 1));
    }
}
