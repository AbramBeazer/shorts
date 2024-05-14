package org.shorts.model.moves.weather;

import org.shorts.battle.Weather;
import org.shorts.model.types.Type;

public class Snowscape extends WeatherMove {

    public Snowscape() {
        super("Snowscape", Weather.SNOW, Type.ICE, 16);
    }
}
