package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Charmander extends Pokemon {

    public Charmander(Ability ability) {
        super("004", null, "CHARMANDER", Set.of(Type.FIRE), ability);
    }
}
