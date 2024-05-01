package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.WeatherAbility.DRIZZLE;
import static org.shorts.model.types.Type.WATER;

public class Kyogre extends Pokemon {

    public Kyogre() {
        super("382", null, "KYOGRE", Set.of(WATER), DRIZZLE);
    }
}
