package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Onix extends Pokemon {

    public Onix(Ability ability) {
        super("095", null, "ONIX", Set.of(Type.ROCK, Type.GROUND), ability);
    }
}
