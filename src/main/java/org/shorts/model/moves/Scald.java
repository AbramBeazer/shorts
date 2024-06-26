package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Scald extends Move implements SelfThawingMove, ThawingMove {

    public Scald() {
        super("Scald", 80, 100, Type.WATER, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 30);
    }

    @Override
    protected void onStartup(Pokemon user) {
        SelfThawingMove.super.thawSelf(user);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}