package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.ExtremeWeatherAbility.PRIMORDIAL_SEA;
import static org.shorts.model.types.Type.WATER;

public class PrimalKyogre extends Pokemon {

    public PrimalKyogre() {
        super("382", null, "Primal Kyogre", Set.of(WATER), PRIMORDIAL_SEA);
    }
}
