package org.shorts.model.moves.multihit;

import org.shorts.model.moves.BulletEffect;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class RockBlast extends MultiHitMove implements BulletEffect {

    public RockBlast() {
        super("Rock Blast", 25, 90, Type.ROCK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0, 2, 5);
    }
}
