package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.GRASS;

public class Rillaboom extends Pokemon {

    public Rillaboom(Ability ability) {
        super("812", null, "Rillaboom", Set.of(GRASS), ability);
    }
}
