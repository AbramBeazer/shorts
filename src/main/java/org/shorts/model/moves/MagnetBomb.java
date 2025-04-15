package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class MagnetBomb extends Move implements BallBombMove {

    public MagnetBomb() {
        super("Magnet Bomb", 60, -1, Type.STEEL, Category.PHYSICAL, Range.NORMAL, 32, false, 0);
    }

    //Not in Gen 9
}
