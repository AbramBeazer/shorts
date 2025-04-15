package org.shorts.model.moves.recoil;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class FlareBlitz extends RecoilAttack implements GetsSheerForceBoost {

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
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.thaw();
        super.execute(user, targets, battle);
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