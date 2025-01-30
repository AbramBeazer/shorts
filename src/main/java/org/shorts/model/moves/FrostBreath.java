package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class FrostBreath extends Move implements AlwaysCritMove {

    public FrostBreath() {
        super("Frost Breath", 60, 190, Type.ICE, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 0);
    }
}
