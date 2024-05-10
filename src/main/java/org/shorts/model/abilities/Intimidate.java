package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class Intimidate extends Ability {

    private Intimidate() {
        super("Intimidate");
    }

    public static final Intimidate INTIMIDATE = new Intimidate();

    @Override
    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        if (!(opponent.getAbility() instanceof StatPreservingAbility)) {
            opponent.changeAttack(-1);
            opponent.afterDrop(self, battle);
        }
    }
}
