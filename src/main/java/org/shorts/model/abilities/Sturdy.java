package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.nullifying.NullifyingAbility;
import org.shorts.model.pokemon.Pokemon;

public class Sturdy extends Ability {

    private Sturdy() {
        super("Sturdy");
    }

    public static final Sturdy STURDY = new Sturdy();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP) {
        if (previousHP == self.getMaxHP() && self.getCurrentHP() == 0
            && !(opponent.getAbility() instanceof NullifyingAbility)) {
            self.setCurrentHP(1);
            System.out.println(self.getNickname() + " held on due to Sturdy!");
        }
    }
}
