package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class AssaultVest extends HeldItem {

    private AssaultVest() {
        super("Assault Vest");
    }

    public static final AssaultVest ASSAULT_VEST = new AssaultVest();

    public void onGainItem(Pokemon self) {
        self.setSpecialDefense(self.getSpecialDefense() * 3 / 2);
    }

    public void onLoseItem(Pokemon self, Pokemon opponent, Battle battle) {
        self.setSpecialDefense(self.getSpecialDefense() * 2 / 3);
    }
}
