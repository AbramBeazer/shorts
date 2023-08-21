package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.types.Type;

public class Onix extends Pokemon {

    public Onix() {
        super(95, null, "ONIX", Set.of(Type.ROCK, Type.GROUND));
    }
}
