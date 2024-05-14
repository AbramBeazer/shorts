package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.ROCK;

public class Aerodactyl extends Pokemon {

    public Aerodactyl(Ability ability) {
        super("142", null, "Aerodactyl", Set.of(ROCK, FLYING), ability);
    }

}
