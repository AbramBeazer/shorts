package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.DARK;
import static org.shorts.model.types.Type.GROUND;

public class Krookodile extends Pokemon {

    public Krookodile(Ability ability) {
        super("553", null, "Krookodile", Set.of(GROUND, DARK), ability);
    }
}
