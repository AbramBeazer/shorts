package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.STEEL;

public class CrownedZamazenta extends Zamazenta {

    public CrownedZamazenta() {
        super();
        this.setPokedexNo("889-C");
        this.setTypes(Set.of(FIGHTING, STEEL));
    }
}
