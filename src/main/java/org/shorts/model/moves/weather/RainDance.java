package org.shorts.model.moves.weather;

import org.shorts.battle.Weather;
import org.shorts.model.types.Type;

public class RainDance extends WeatherMove {

    public RainDance() {
        super("Rain Dance", Weather.RAIN, Type.WATER);
    }
}
