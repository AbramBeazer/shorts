package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.DRAGON;
import static org.shorts.model.types.Type.GHOST;

public class Giratina extends Pokemon {

    public Giratina(Ability ability) {
        super("487", null, "Giratina", Set.of(GHOST, DRAGON), ability);
    }
}
