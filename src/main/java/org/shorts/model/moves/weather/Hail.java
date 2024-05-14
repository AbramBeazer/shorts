package org.shorts.model.moves.weather;

import org.shorts.battle.Weather;
import org.shorts.model.types.Type;

public class Hail extends WeatherMove {

    public Hail() {
        super("Hail", Weather.HAIL, Type.ICE, 16);
    }
}
