package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Axew extends Pokemon {

    public Axew(Ability ability) {
        super("610", null, "AXEW", Set.of(Type.DRAGON), ability);
    }
}
