package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.types.Type;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super(1, null, "BULBASAUR", Set.of(Type.GRASS, Type.POISON));
    }
}
