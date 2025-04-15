package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class FlowerTrick extends Move implements AlwaysCritMove {

    public FlowerTrick() {
        super("Flower Trick", 70, -1, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 16, false, 0);
    }
}
