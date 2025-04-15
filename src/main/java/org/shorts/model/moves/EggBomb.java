package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class EggBomb extends Move implements BallBombMove {

    public EggBomb() {
        super("Egg Bomb", 100, 75, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, false, 0);
    }

    //Not in Gen 8 or 9
}
