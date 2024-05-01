package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.ExtremeWeatherAbility.DESOLATE_LAND;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.GROUND;

public class PrimalGroudon extends Pokemon {

    public PrimalGroudon() {
        super("383", null, "PRIMAL GROUDON", Set.of(GROUND, FIRE), DESOLATE_LAND);
    }
}
