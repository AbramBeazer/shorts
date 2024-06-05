package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;

public class CudChew extends Ability {

    private Berry berryToRegurgitate;

    public CudChew() {
        super("Cud Chew");
    }

    public Berry getBerryToRegurgitate() {
        return berryToRegurgitate;
    }

    public void setBerryToRegurgitate(Berry berryToRegurgitate) {
        this.berryToRegurgitate = berryToRegurgitate;
    }

    @Override
    public void afterTurn(Pokemon self, Pokemon opponent, Battle battle) {
        if (berryToRegurgitate != null) {
            berryToRegurgitate.doEffect(self);
            berryToRegurgitate = null;
        }
    }
}
