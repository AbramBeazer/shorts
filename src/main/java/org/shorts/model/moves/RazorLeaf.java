package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class RazorLeaf extends Move implements SlicingMove, HighCritChanceMove {

    public RazorLeaf() {
        super("Razor Leaf", 55, 95, Type.GRASS, Category.PHYSICAL, Range.ALL_ADJACENT_OPPONENTS, 40, false, 0);
    }
}
