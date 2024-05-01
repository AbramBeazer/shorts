package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Sneasel extends Pokemon {

    public Sneasel(Ability ability) {
        super("215", null, "SNEASEL", Set.of(Type.DARK, Type.ICE), ability);
    }
}
