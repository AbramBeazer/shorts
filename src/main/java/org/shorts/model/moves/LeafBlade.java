package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class LeafBlade extends Move implements SlicingMove, HighCritChanceMove {

    public LeafBlade() {
        super("Leaf Blade", 90, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }
}

