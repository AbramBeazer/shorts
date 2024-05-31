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
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        return super.rollToHit(user, target, battle);
    }
}
