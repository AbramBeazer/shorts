package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.BURN;

public class WillOWisp extends Move implements AffectedByMagicBounce {

    public WillOWisp() {
        super("Will-O-Wisp", 0, 85, Type.FIRE, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
