package org.shorts.model.abilities;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

public class Guts extends Ability {

    static final double GUTS_MULTIPLIER = 1.5;

    private Guts() {
        super("Guts");
    }

    @Override
    public double onCalculateAttack(Pokemon self) {
        if (self.getStatus() == Status.NONE || self.getStatus() == Status.FREEZE) {
            return 1;
        } else {
            return GUTS_MULTIPLIER;
        }
    }

    public static final Guts GUTS = new Guts();
}
