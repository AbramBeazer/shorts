package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class BlazeKick extends Move implements HighCritChanceMove, GetsSheerForceBoost, KickingMove {

    public BlazeKick() {
        super("Blaze Kick", 85, 90, Type.FIRE, Category.PHYSICAL, Range.NORMAL, 16, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
