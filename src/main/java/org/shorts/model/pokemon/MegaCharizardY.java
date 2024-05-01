package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.weather.WeatherAbility.DROUGHT;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.FLYING;

public class MegaCharizardY extends Pokemon {

    public MegaCharizardY() {
        super("006", null, "Mega Charizard-Y", Set.of(FIRE, FLYING), DROUGHT);
    }
}
