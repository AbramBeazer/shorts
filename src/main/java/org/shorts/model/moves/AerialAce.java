package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class AerialAce extends Move implements SlicingMove {

    public AerialAce() {
        super("Aerial Ace", 60, -1, Type.FLYING, Category.PHYSICAL, Range.SINGLE_ANY, 32, true, 0);
    }
}
