package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.items.NoItem.NO_ITEM;

public class Harvest extends Ability {

    private Harvest() {
        super("Harvest");
    }

    public static final Harvest HARVEST = new Harvest();

    @Override
    public void afterTurn(Pokemon self, Pokemon opponent, Battle battle) {
        if (self.getHeldItem() == NO_ITEM && self.getConsumedItem() instanceof Berry
            && (battle.getWeather() == Weather.SUN || battle.getWeather() == Weather.EXTREME_SUN
            || RANDOM.nextInt(2) == 0)) {

            self.setHeldItem(self.getConsumedItem());
            self.setConsumedItem(NO_ITEM);
        }
    }
}
