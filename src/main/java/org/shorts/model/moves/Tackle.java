package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Tackle extends Move {

    public Tackle() {
        super("Tackle", 40, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 56, true, 0);
    }
}
