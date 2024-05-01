package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Golduck extends Pokemon {

    public Golduck(Ability ability) {
        super("055", null, "GOLDUCK", Set.of(Type.WATER), ability);
    }
}
