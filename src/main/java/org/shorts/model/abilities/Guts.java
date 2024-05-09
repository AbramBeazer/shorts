package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

public class Guts extends Ability {

    private Guts() {
        super("Guts");
    }

    @Override
    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
        if (self.getStatus() == Status.NONE) {
            self.setAttack(self.getAttack() / 2);
        } else {
            self.setAttack(self.getAttack() * 2);
        }
    }

    public static final Guts GUTS = new Guts();
}
