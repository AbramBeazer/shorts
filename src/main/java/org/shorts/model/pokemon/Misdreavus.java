package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.Levitate.LEVITATE;
import static org.shorts.model.types.Type.GHOST;

public class Misdreavus extends Pokemon {

    public Misdreavus() {
        super("200", null, "Misdreavus", Set.of(GHOST), LEVITATE);
    }
}
