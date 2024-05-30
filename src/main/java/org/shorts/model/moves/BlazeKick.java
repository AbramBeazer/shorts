package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class BlazeKick extends Move implements HighCritChanceMove {

    public BlazeKick() {
        super("Blaze Kick", 85, 90, Type.FIRE, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.getStatus().getType().equals(StatusType.FREEZE)) {
            System.out.println(target.getNickname() + " was thawed out!");
            target.setStatus(Status.NONE);
        }
        if (StatusType.BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
