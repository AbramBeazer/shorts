package org.shorts.model.moves.multihit;

import org.shorts.model.moves.BallBombMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class RockBlast extends MultiHitMove implements BallBombMove {

    public RockBlast() {
        super("Rock Blast", 25, 90, Type.ROCK, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 0, 2, 5);
    }
}
