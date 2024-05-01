package org.shorts.model.abilities.weather;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.pokemon.Pokemon;

public class ExtremeWeatherAbility extends WeatherAbility {

    private ExtremeWeatherAbility(String name, Weather weather) {
        super(name, weather);
    }

    public static final ExtremeWeatherAbility PRIMORDIAL_SEA = new ExtremeWeatherAbility(
        "Primordial Sea",
        Weather.EXTREME_RAIN);
    public static final ExtremeWeatherAbility DESOLATE_LAND = new ExtremeWeatherAbility(
        "Desolate Land",
        Weather.EXTREME_SUN);
    public static final ExtremeWeatherAbility DELTA_STREAM = new ExtremeWeatherAbility(
        "Delta Stream",
        Weather.EXTREME_WIND);

    @Override
    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        battle.setWeather(this.getWeather(), Weather.INFINITE_WEATHER_DURATION);
    }

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        super.beforeSwitchOut(self, opponent, battle);
    }

}
