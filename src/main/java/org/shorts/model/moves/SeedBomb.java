package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SeedBomb extends Move implements BallBombMove {

    public SeedBomb() {
        super("Seed Bomb", 80, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, false, 0);
    }

}
