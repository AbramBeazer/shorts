package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

public class Defiant extends Ability {

    private Defiant() {
        super("Defiant");
    }

    public static final Defiant DEFIANT = new Defiant();

    @Override
    public void afterDrop(Pokemon self, Pokemon cause, Battle battle) {
        if (battle.areOpponents(self, cause) && self.canChangeStat(2, StatEnum.ATK)) {

            self.changeStat(battle, self, 2, StatEnum.ATK);
        }
    }
}
