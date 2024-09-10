package org.shorts.model.moves.thawing;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class SteamEruption extends ThawingMove implements GetsSheerForceBoost {

    public SteamEruption() {
        super("Steam Eruption", 110, 95, Type.WATER, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
