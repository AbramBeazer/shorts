package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.WeatherAbility.SAND_STREAM;
import static org.shorts.model.types.Type.DARK;
import static org.shorts.model.types.Type.ROCK;

public class MegaTyranitar extends Pokemon {

    public MegaTyranitar() {
        super("248", null, "MEGA TYRANITAR", Set.of(ROCK, DARK), SAND_STREAM);
    }
}
