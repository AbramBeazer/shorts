package org.shorts.model.moves.weather;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.items.WeatherExtendingItem;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.battle.Weather.DEFAULT_WEATHER_DURATION;
import static org.shorts.battle.Weather.EXTREME_RAIN;
import static org.shorts.battle.Weather.EXTREME_SUN;
import static org.shorts.battle.Weather.EXTREME_WIND;

public abstract class WeatherMove extends Move {

    private Weather weather;

    public WeatherMove(String name, Weather weather, Type type, int maxPP) {
        super(name, 0, -1, type, Category.STATUS, Range.BOTH_SIDES, maxPP, false, 100);
        this.weather = weather;
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final Weather currentWeather = battle.getWeather();
        if (EXTREME_WIND == currentWeather || EXTREME_RAIN == currentWeather || EXTREME_SUN == currentWeather
            || weather == currentWeather) {
            System.out.println("...but it failed!");
        } else {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        battle.setWeather(weather, DEFAULT_WEATHER_DURATION);
        if (user.getHeldItem() instanceof WeatherExtendingItem) {
            WeatherExtendingItem weatherExtendingItem = (WeatherExtendingItem) user.getHeldItem();
            weatherExtendingItem.extendWeather(battle);
        }
    }
}
