package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Aeroblast extends Move implements HighCritChanceMove, WindMove {

    public Aeroblast() {
        super("Aeroblast", 100, 95, Type.FLYING, Category.SPECIAL, Range.SINGLE_ANY, 8, false, 0);
    }
}
