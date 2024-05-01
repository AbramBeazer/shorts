package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GROUND;

public class Hippopotas extends Pokemon {

    public Hippopotas(Ability ability) {
        super("449", null, "HIPPOPOTAS", Set.of(GROUND), ability);
    }
}
