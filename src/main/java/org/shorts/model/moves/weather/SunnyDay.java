package org.shorts.model.moves.weather;

import org.shorts.battle.Weather;
import org.shorts.model.types.Type;

public class SunnyDay extends WeatherMove {

    public SunnyDay() {
        super("Sunny Day", Weather.SUN, Type.FIRE, 8);
    }
}
