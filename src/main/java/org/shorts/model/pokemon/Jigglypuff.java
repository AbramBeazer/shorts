package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class Jigglypuff extends Pokemon {

    public Jigglypuff(Ability ability) {
        super("039", null, "JIGGLYPUFF", Set.of(Type.NORMAL, Type.FAIRY), ability);
    }
}
