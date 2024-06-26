package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

public class Moxie extends Ability {

    private Moxie() {
        super("Moxie");
    }

    public static final Moxie MOXIE = new Moxie();

    @Override
    public void afterKO(Pokemon self, Pokemon opponent, Battle battle) {
        self.changeStat(1, StatEnum.ATK);
    }
}
