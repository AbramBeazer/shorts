package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Pikachu extends Pokemon {

    public Pikachu(Ability ability) {
        super("025", null, "PIKACHU", Set.of(Type.ELECTRIC), ability);
    }
}
