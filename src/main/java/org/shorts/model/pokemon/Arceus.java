package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.Multitype.MULTITYPE;
import static org.shorts.model.types.Type.NORMAL;

public class Arceus extends Pokemon {

    public Arceus() {
        super("493", null, "Arceus", Set.of(NORMAL), MULTITYPE);
    }
}
