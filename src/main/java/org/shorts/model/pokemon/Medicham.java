package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Medicham extends Pokemon {

    public Medicham(Ability ability) {
        super("308", null, "MEDICHAM", Set.of(Type.FIGHTING, Type.PSYCHIC), ability);
    }
}
