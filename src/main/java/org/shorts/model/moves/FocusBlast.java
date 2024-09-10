package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FocusBlast extends Move implements BallBombMove, GetsSheerForceBoost {

    public FocusBlast() {
        super("Focus Blast", 120, 70, Type.FIGHTING, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPDEF)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.SPDEF);
    }
}
