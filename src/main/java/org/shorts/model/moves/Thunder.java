package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Thunder extends Move implements GetsSheerForceBoost {

    public Thunder() {
        super("Thunder", 110, 70, Type.ELECTRIC, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.PARALYZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.PARALYZE);
    }

}
