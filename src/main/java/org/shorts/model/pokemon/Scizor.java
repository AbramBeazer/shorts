package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Scizor extends Pokemon {

    public Scizor(Ability ability) {
        super("212", null, "SCIZOR", Set.of(Type.BUG, Type.STEEL), ability);
    }
}
