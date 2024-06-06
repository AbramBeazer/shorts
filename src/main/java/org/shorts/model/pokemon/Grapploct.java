package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.FIGHTING;

public class Grapploct extends Pokemon {

    public Grapploct(Ability ability) {
        super("853", null, "Grapploct", Set.of(FIGHTING), ability);
    }
}
