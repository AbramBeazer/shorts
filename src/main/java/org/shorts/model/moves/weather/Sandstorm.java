package org.shorts.model.moves.weather;

import org.shorts.battle.Weather;
import org.shorts.model.types.Type;

public class Sandstorm extends WeatherMove {

    public Sandstorm() {
        super("Sandstorm", Weather.SAND, Type.ROCK, 16);
    }
}
