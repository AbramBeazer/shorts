package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MistBall extends Move implements BallBombMove, GetsSheerForceBoost {

    public MistBall() {
        super("Mist Ball", 95, 100, Type.PSYCHIC, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 50);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPATK)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.SPATK);
    }
}
