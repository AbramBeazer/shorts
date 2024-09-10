package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MudBomb extends Move implements BallBombMove, GetsSheerForceBoost {

    public MudBomb() {
        super("Mud Bomb", 65, 85, Type.GROUND, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.ACCURACY)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.ACCURACY);
    }
}
