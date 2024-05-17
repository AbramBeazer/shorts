package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;

import static org.shorts.model.types.Type.DARK;
import static org.shorts.model.types.Type.ROCK;

public class Tyranitar extends Pokemon {

    public Tyranitar(Ability ability) {
        super("248", null, "Tyranitar", Set.of(ROCK, DARK), ability);
    }
}
