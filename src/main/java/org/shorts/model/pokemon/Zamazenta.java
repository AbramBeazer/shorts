package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.DauntlessShield.DAUNTLESS_SHIELD;
import static org.shorts.model.types.Type.FIGHTING;

public class Zamazenta extends Pokemon {

    public Zamazenta() {
        super("889", null, "Zamazenta", Set.of(FIGHTING), DAUNTLESS_SHIELD);
    }
}
