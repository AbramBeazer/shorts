package org.shorts.model.moves.multihit;

import org.shorts.model.moves.BallBombMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Barrage extends MultiHitMove implements BallBombMove {

    public Barrage() {
        super("Barrage", 15, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, false, 0, 2, 5);
    }
}