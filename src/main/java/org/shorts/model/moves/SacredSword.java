package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class SacredSword extends Move implements SlicingMove {

    public SacredSword() {
        super("Sacred Sword", 90, 100, Type.FIGHTING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 0);
    }

    @Override
    protected int getDefendingStat(Pokemon attacker, Pokemon defender, Battle battle) {
        //TODO: When it says it ignores changes to defense, does that just mean stage?
        return defender.calculateDefenseIgnoreStage();
    }

    @Override
    protected double getAccuracyEvasionStageModifier(Pokemon user, Pokemon target) {
        int combinedStage = Math.max(-6, Math.min(user.getStageAccuracy(), 6));
        if (combinedStage < 0) {
            return 3d / Math.abs(combinedStage - 3);
        } else {
            return (combinedStage + 3) / 3d;
        }
    }
}
