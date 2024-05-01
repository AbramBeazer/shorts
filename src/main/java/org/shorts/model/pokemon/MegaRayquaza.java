package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.ExtremeWeatherAbility.DELTA_STREAM;
import static org.shorts.model.types.Type.DRAGON;
import static org.shorts.model.types.Type.FLYING;

public class MegaRayquaza extends Pokemon {

    public MegaRayquaza() {
        super("384", null, "MEGA RAYQUAZA", Set.of(DRAGON, FLYING), DELTA_STREAM);
    }
}
