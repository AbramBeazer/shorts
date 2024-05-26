package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.Status.PARALYZE;

public class BodySlam extends PhysicalMove implements HitsMinimize {
    public BodySlam() {
        super("Body Slam", 85, 100, Type.NORMAL, 24, true, 30);
    }


    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (PARALYZE.isStatusPossible(defender, battle)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(PARALYZE);
    }

    @Override
    public boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        return HitsMinimize.super.targetIsMinimized(target) || super.rollToHit(user, target, battle);
    }

    @Override
    public double getOtherMultiplier(Pokemon user, Pokemon target, Battle battle) {
        return HitsMinimize.super.targetIsMinimized(target) ? 2 : 1;
    }


}
