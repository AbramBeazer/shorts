package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Bulbasaur extends Pokemon {

    public Bulbasaur(Ability ability) {
        super("001", null, "BULBASAUR", Set.of(Type.GRASS, Type.POISON), ability);
    }
}
