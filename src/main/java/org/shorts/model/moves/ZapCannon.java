package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.PARALYZE;

public class ZapCannon extends Move implements BallBombMove {

    public ZapCannon() {
        super("Zap Cannon", 120, 50, Type.ELECTRIC, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (PARALYZE.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.PARALYZE);
    }
}
