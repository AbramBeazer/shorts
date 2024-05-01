package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GRASS;
import static org.shorts.model.types.Type.ICE;

public class Abomasnow extends Pokemon {

    public Abomasnow(Ability ability) {
        super("460", null, "ABOMASNOW", Set.of(ICE, GRASS), ability);
    }
}
