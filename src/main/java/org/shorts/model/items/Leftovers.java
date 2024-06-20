package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class Leftovers extends HeldItem {

    private Leftovers() {
        super("Leftovers");
    }

    public static final Leftovers LEFTOVERS = new Leftovers();

    @Override
    public void afterTurn(Pokemon user, Battle battle) {
        if (user.getCurrentHP() < user.getMaxHP() && !user.hasFainted()) {
            int healing = Math.max(user.getMaxHP() / 16, 1);
            user.heal(healing);
        }
    }
}
