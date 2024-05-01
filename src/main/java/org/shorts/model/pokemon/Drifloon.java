package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Drifloon extends Pokemon {

    public Drifloon(Ability ability) {
        super("425", null, "DRIFLOON", Set.of(Type.GHOST, Type.FLYING), ability);
    }
}
