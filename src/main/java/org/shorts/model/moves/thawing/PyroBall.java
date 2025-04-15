package org.shorts.model.moves.thawing;

import org.shorts.battle.Battle;
import org.shorts.model.moves.BallBombMove;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.BURN;

public class PyroBall extends ThawingMove implements BallBombMove, GetsSheerForceBoost {

    public PyroBall() {
        super("Pyro Ball", 120, 90, Type.FIRE, Category.SPECIAL, Range.NORMAL, 8, false, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (BURN.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
