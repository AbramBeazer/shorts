package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.RKSSystem.RKS_SYSTEM;
import static org.shorts.model.types.Type.NORMAL;

public class Silvally extends Pokemon {

    public Silvally() {
        super("773", null, "Silvally", Set.of(NORMAL), RKS_SYSTEM);
    }
}
