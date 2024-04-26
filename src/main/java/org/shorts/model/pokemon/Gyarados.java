package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.types.Type;

public class Gyarados extends Pokemon {

    public Gyarados() {
        super("130", null, "Gyarados", Set.of(Type.WATER, Type.FLYING));
    }
}
