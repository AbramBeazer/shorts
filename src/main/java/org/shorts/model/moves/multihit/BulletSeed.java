package org.shorts.model.moves.multihit;

import org.shorts.model.moves.BulletEffect;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BulletSeed extends MultiHitMove implements BulletEffect {

    public BulletSeed() {
        super("Bullet Seed", 25, 100, Type.GRASS, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 48, false, 0, 2, 5);
    }
}
