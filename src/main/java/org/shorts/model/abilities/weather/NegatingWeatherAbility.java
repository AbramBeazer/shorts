package org.shorts.model.abilities.weather;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Ability;
import org.shorts.model.pokemon.Pokemon;

public class NegatingWeatherAbility extends Ability {

    private NegatingWeatherAbility(String name) {
        super(name);
    }

    @Override
    public void afterEntry(Pokemon self, Battle battle) {
        battle.setWeatherSuppressed(true);
        System.out.println("The effects of weather disappeared.");
    }

    @Override
    public void afterFaint(Pokemon self, Battle battle) {
        battle.setWeatherSuppressed(false);
        System.out.println("The effects of weather returned.");
    }

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        battle.setWeatherSuppressed(false);
        System.out.println("The effects of weather returned.");
    }

    public static final NegatingWeatherAbility CLOUD_NINE = new NegatingWeatherAbility("Cloud Nine");
    public static final NegatingWeatherAbility AIR_LOCK = new NegatingWeatherAbility("Air Lock");
}
