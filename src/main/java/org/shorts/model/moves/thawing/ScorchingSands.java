package org.shorts.model.moves.thawing;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class ScorchingSands extends ThawingMove implements GetsSheerForceBoost {

    public ScorchingSands() {
        super("Scorching Sands", 70, 100, Type.GROUND, Category.SPECIAL, Range.NORMAL, 16, false, 30);
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
