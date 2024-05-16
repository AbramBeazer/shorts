package org.shorts.model.pokemon;

import java.util.Set;

import static org.shorts.model.abilities.IntrepidSword.INTREPID_SWORD;
import static org.shorts.model.types.Type.FAIRY;

public class Zacian extends Pokemon {

    public Zacian() {
        super("888", null, "Zacian", Set.of(FAIRY), INTREPID_SWORD);
    }
}
