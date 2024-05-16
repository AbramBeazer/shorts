package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.types.Type.FAIRY;
import static org.shorts.model.types.Type.STEEL;

public class CrownedZacian extends Zacian {

    public CrownedZacian() {
        super();
        this.setPokedexNo("888-C");
        this.setTypes(Set.of(FAIRY, STEEL));
    }
}
