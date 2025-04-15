package org.shorts.model.moves.multihit;

import org.shorts.model.moves.BallBombMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BulletSeed extends MultiHitMove implements BallBombMove {

    public BulletSeed() {
        super("Bullet Seed", 25, 100, Type.GRASS, Category.PHYSICAL, Range.NORMAL, 48, false, 0, 2, 5);
    }
}
