package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GROUND;

public class Hippowdon extends Pokemon {

    public Hippowdon(Ability ability) {
        super("450", null, "HIPPOWDON", Set.of(GROUND), ability);
    }
}
