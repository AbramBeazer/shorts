package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class Sturdy extends Ability implements IgnorableAbility {

    private Sturdy() {
        super("Sturdy");
    }

    public static final Sturdy STURDY = new Sturdy();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (previousHP == self.getMaxHP() && self.getCurrentHP() == 0 && !isIgnored(self)) {
            self.setCurrentHP(1);
            System.out.println(self.getNickname() + " held on due to Sturdy!");
        }
    }
}
