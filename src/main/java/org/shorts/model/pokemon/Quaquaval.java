package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Quaquaval extends Pokemon {

    private static final Set<Type> TYPES = Set.of(Type.WATER, Type.FIGHTING);

    public Quaquaval(Ability ability) {
        super("914", null, "Quaquaval", TYPES, ability);
    }
}
