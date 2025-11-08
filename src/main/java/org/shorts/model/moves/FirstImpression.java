package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FirstImpression extends Move {

    public FirstImpression() {
        super("First Impression", 90, 100, Type.BUG, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }
    @Override
    public void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        if (user.getTurnsInBattle() == 1) {
            super.executeOnTarget(user, target, battle);
        } else {
            System.out.println("But it failed!");
        }
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 2;
    }
}
