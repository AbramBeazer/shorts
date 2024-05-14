package org.shorts.model.moves.weather;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.items.WeatherExtendingItem;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.battle.Weather.DEFAULT_WEATHER_DURATION;
import static org.shorts.battle.Weather.EXTREME_RAIN;
import static org.shorts.battle.Weather.EXTREME_SUN;
import static org.shorts.battle.Weather.EXTREME_WIND;

public abstract class WeatherMove extends StatusMove {

    private Weather weather;

    public WeatherMove(String name, Weather weather, Type type, int maxPP) {
        super(name, -1, type, maxPP, false);
        this.weather = weather;
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        final Weather currentWeather = battle.getWeather();
        if (EXTREME_WIND == currentWeather || EXTREME_RAIN == currentWeather || EXTREME_SUN == currentWeather
            || weather == currentWeather) {
            System.out.println("...but it failed!");
        } else {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        battle.setWeather(weather, DEFAULT_WEATHER_DURATION);
        if (attacker.getHeldItem() instanceof WeatherExtendingItem) {
            WeatherExtendingItem weatherExtendingItem = (WeatherExtendingItem) attacker.getHeldItem();
            weatherExtendingItem.extendWeather(battle);
        }
    }
}
