package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class Regenerator extends Ability {

    private Regenerator() {
        super("Regenerator");
    }

    public static final Regenerator REGENERATOR = new Regenerator();

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        self.heal(self.getMaxHP() / 3);
    }
}
