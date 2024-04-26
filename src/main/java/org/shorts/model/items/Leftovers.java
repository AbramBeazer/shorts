package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class Leftovers extends HeldItem {

    private Leftovers() {
        super("Leftovers");
    }

    public static final Leftovers LEFTOVERS = new Leftovers();

    @Override
    public void afterTurn(Pokemon self, Pokemon opponent, Battle battle) {
        if (self.getCurrentHP() < self.getMaxHP() && !self.hasFainted()) {
            int healing = Math.max(self.getMaxHP() / 16, 1);
            self.heal(healing);
        }
    }
}
