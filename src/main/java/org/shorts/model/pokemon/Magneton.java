package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.STEEL;

public class Magneton extends Pokemon {

    public Magneton(Ability ability) {
        super("082", null, "Magneton", Set.of(ELECTRIC, STEEL), ability);
    }
}
