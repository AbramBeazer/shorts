package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GROUND;

public class Sandaconda extends Pokemon {

    public Sandaconda(Ability ability) {
        super("844", null, "SANDACONDA", Set.of(GROUND), ability);
    }
}
