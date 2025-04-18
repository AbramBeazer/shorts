package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class HeatCrash extends Move implements HitsMinimize {

    public HeatCrash() {
        super("Heat Crash", 0, 100, Type.FIRE, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        final double weightRatio = target.getWeight() / user.getWeight();
        if (weightRatio > .5) {
            return 40;
        } else if (weightRatio >= .3335) {
            return 60;
        } else if (weightRatio >= .2501) {
            return 80;
        } else if (weightRatio >= .2001) {
            return 100;
        } else {
            return 120;
        }
    }
}
