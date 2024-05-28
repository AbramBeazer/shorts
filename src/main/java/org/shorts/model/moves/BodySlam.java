package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.Status.PARALYZE;
import static org.shorts.model.status.VolatileStatusType.MINIMIZED;

public class BodySlam extends Move implements HitsMinimize {

    public BodySlam() {
        super("Body Slam", 85, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 30);
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
        return target.hasVolatileStatus(MINIMIZED) || super.rollToHit(user, target, battle);
    }
}
