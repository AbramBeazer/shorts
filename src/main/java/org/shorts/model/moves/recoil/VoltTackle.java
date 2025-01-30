package org.shorts.model.moves.recoil;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class VoltTackle extends RecoilAttack implements GetsSheerForceBoost {

    public VoltTackle() {
        super(
            "Volt Tackle",
            120,
            100,
            Type.ELECTRIC,
            Category.PHYSICAL,
            Range.NORMAL_SINGLE_ADJACENT_ANY,
            24,
            true,
            10,
            1 / 3d);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.PARALYZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.PARALYZE);
    }
}
