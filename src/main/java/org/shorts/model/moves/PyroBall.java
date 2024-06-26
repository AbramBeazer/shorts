package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.BURN;

public class PyroBall extends Move implements BallBombMove, SelfThawingMove {

    public PyroBall() {
        super("Pyro Ball", 120, 90, Type.FIRE, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 10);
    }

    @Override
    protected void onStartup(Pokemon user) {
        SelfThawingMove.super.thawSelf(user);
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
