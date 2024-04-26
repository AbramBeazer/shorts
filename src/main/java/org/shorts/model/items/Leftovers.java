package org.shorts.model.items;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.Activation.AFTER_TURN;

public class Leftovers extends HeldItem {

    public Leftovers() {
        super("Leftovers", AFTER_TURN);
    }

    public static final Leftovers LEFTOVERS = new Leftovers();

    @Override
    public void activate(Pokemon self, Pokemon opponent) {
        if (self.getCurrentHP() < self.getMaxHP()) {
            int healing = Math.max(self.getMaxHP() / 16, 1);
            self.heal(healing);
        }
    }
}
