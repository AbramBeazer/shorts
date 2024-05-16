package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.Download.DOWNLOAD;
import static org.shorts.model.types.Type.BUG;
import static org.shorts.model.types.Type.STEEL;

public class Genesect extends Pokemon {

    public Genesect() {
        super("649", null, "Genesect", Set.of(BUG, STEEL), DOWNLOAD);
    }

}
