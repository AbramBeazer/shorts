package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.WeatherAbility.DROUGHT;
import static org.shorts.model.types.Type.GROUND;

public class Groudon extends Pokemon {

    public Groudon() {
        super("383", null, "GROUDON", Set.of(GROUND), DROUGHT);
    }
}
