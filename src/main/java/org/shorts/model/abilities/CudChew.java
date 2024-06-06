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
        //TODO: What if the user's ability changes after eating the berry but before regurgitating it? Does it still get the effect again? If it does, I'll need to move the berryToRegurgitate field into a volatile RegurgitateStatus.
        if (berryToRegurgitate != null) {
            berryToRegurgitate.doEffect(self);
            berryToRegurgitate = null;
        }
    }
}
