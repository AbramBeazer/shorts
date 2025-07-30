package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class PetalBlizzard extends Move implements WindMove {

    public PetalBlizzard() {
        super("Petal Blizzard", 90, 100, Type.GRASS, Category.PHYSICAL, Range.ALL_ADJACENT, 24, false, 0);
    }
}
