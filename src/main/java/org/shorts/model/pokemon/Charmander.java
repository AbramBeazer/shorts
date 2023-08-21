package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.types.Type;

public class Charmander extends Pokemon {

    public Charmander() {
        super(4, null, "CHARMANDER", Set.of(Type.FIRE));
    }
}
