package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;

public class FlameOrb extends HeldItem {

    private FlameOrb() {
        super("Flame Orb", 30);
    }

    public static final FlameOrb FLAME_ORB = new FlameOrb();

    @Override
    public void afterTurn(Pokemon user, Battle battle) {
        if (StatusType.BURN.isStatusPossible(user, user, battle)) {
            user.setStatus(Status.BURN);
        }
    }
}
