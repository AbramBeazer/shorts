package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;

import static org.shorts.battle.Weather.EXTENDED_WEATHER_DURATION;

public class WeatherExtendingItem extends HeldItem {

    private final Weather weather;

    private WeatherExtendingItem(String name, Weather weather, int flingPower) {
        super(name, flingPower);
        this.weather = weather;
    }

    public void extendWeather(Battle battle) {
        if (this.weather.equals(battle.getWeather())) {
            battle.setWeather(weather, EXTENDED_WEATHER_DURATION);
        }
    }

    public static final WeatherExtendingItem DAMP_ROCK = new WeatherExtendingItem("Damp Rock", Weather.RAIN, 60);
    public static final WeatherExtendingItem ICY_ROCK = new WeatherExtendingItem("Icy Rock", Weather.HAIL, 40);
    public static final WeatherExtendingItem HEAT_ROCK = new WeatherExtendingItem("Heat Rock", Weather.SUN, 60);
    public static final WeatherExtendingItem SMOOTH_ROCK = new WeatherExtendingItem("Smooth Rock", Weather.SAND, 10);

}
