package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

public class Competitive extends Ability {

    private Competitive() {
        super("Competitive");
    }

    public static final Competitive COMPETITIVE = new Competitive();

    @Override
    public void afterDrop(Pokemon self, Pokemon cause, Battle battle) {
        if (battle.areOpponents(self, cause) && self.canChangeStat(2, StatEnum.SPATK)) {

            self.changeStat(battle, self, 2, StatEnum.SPATK);
        }
    }
}
