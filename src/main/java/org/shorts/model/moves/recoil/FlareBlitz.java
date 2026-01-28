package org.shorts.model.moves.recoil;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.thawing.ThawingMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class FlareBlitz extends RecoilAttack implements GetsSheerForceBoost, ThawingMove {

    public FlareBlitz() {
        super(
            "Flare Blitz",
            120,
            100,
            Type.FIRE,
            Category.PHYSICAL,
            Range.NORMAL,
            24,
            true,
            10,
            1 / 3d);
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