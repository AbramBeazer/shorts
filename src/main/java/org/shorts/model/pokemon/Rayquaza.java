package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.NegatingWeatherAbility.AIR_LOCK;
import static org.shorts.model.types.Type.DRAGON;
import static org.shorts.model.types.Type.FLYING;

public class Rayquaza extends Pokemon {

    public Rayquaza() {
        super("384", null, "RAYQUAZA", Set.of(DRAGON, FLYING), AIR_LOCK);
    }
}
