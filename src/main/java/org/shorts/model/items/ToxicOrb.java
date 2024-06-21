package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;

public class ToxicOrb extends HeldItem {

    private ToxicOrb() {
        super("Toxic Orb", 30);
    }

    public static final ToxicOrb TOXIC_ORB = new ToxicOrb();

    @Override
    public void afterTurn(Pokemon user, Battle battle) {
        if (StatusType.TOXIC_POISON.isStatusPossible(user, battle)) {
            user.setStatus(Status.TOXIC_POISON);
        }
    }
}
