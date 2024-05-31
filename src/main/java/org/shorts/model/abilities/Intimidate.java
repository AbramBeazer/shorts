package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.Rattled.RATTLED;
import static org.shorts.model.abilities.StatusImmuneAbility.OWN_TEMPO;

public class Intimidate extends Ability {

    private Intimidate() {
        super("Intimidate");
    }

    public static final Intimidate INTIMIDATE = new Intimidate();

    @Override
    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        if (!(opponent.getAbility() instanceof StatPreservingAbility) && !opponent.getAbility().equals(OWN_TEMPO)) {
            opponent.changeAttack(-1);
            opponent.afterDrop(self, battle);
            if (opponent.getAbility() == RATTLED) {
                opponent.changeSpeed(1);
            }
        }
    }
}
