package org.shorts.model.abilities.weather;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.abilities.Ability;
import org.shorts.model.items.WeatherExtendingItem;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.battle.Weather.DEFAULT_WEATHER_DURATION;

public class WeatherAbility extends Ability {

    private final Weather weather;

    protected WeatherAbility(String name, Weather weather) {
        super(name);
        this.weather = weather;
    }

    public Weather getWeather() {
        return this.weather;
    }

    @Override
    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        if (!battle.getWeather().isExtreme()) {
            battle.setWeather(this.weather, DEFAULT_WEATHER_DURATION);
            if (self.getHeldItem() instanceof WeatherExtendingItem) {
                WeatherExtendingItem weatherExtendingItem = (WeatherExtendingItem) self.getHeldItem();
                weatherExtendingItem.extendWeather(battle);
            }
        }
    }

    public static final WeatherAbility DRIZZLE = new WeatherAbility("Drizzle", Weather.RAIN);
    public static final WeatherAbility DROUGHT = new WeatherAbility("Drought", Weather.SUN);
    public static final WeatherAbility ORICHALCUM_PULSE = new WeatherAbility("Orichalcum Pulse", Weather.SUN);
    public static final WeatherAbility SNOW_WARNING = new WeatherAbility("Snow Warning", Weather.HAIL);
    public static final WeatherAbility SAND_STREAM = new WeatherAbility("Sand Stream", Weather.SAND);
    public static final WeatherAbility SAND_SPIT = new WeatherAbility("Sand Spit", Weather.SAND);

}
