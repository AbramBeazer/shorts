package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class OranBerry extends Berry {

    private OranBerry() {
        super("Oran");
    }

    @Override
    public void onEat(Pokemon user, Battle battle) {
        user.heal(10 * (user.getAbility() == RIPEN ? 2 : 1));
        super.onEat(user, battle);
    }
}
