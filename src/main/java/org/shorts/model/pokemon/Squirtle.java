package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Squirtle extends Pokemon {

    public Squirtle(Ability ability) {
        super("007", null, "Squirtle", Set.of(Type.WATER), ability);
    }
}
