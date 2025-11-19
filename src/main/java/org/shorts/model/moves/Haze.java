package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Haze extends Move {

    public Haze() {
        super("Haze", 0, -1, Type.ICE, Category.STATUS, Range.ALL, 48, false, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStageAttack(0);
        target.setStageDefense(0);
        target.setStageSpecialAttack(0);
        target.setStageSpecialDefense(0);
        target.setStageSpeed(0);
        target.setStageEvasion(0);
        target.setStageAccuracy(0);
    }
}
