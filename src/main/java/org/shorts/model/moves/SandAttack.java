package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SandAttack extends Move {

    public SandAttack() {
        super("Sand Attack", 0, 100, Type.GROUND, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
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

    @Override
    protected double getTypeMultiplier(Pokemon user, Pokemon target, Battle battle) {
        //Sand Attack can hit Flying-types.
        return 1;
    }
}
