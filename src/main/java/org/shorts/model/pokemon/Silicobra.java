package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GROUND;

public class Silicobra extends Pokemon {

    public Silicobra(Ability ability) {
        super("843", null, "SILICOBRA", Set.of(GROUND), ability);
    }
}
