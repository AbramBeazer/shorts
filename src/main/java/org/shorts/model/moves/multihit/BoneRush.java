package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BoneRush extends MultiHitMove {

    public BoneRush() {
        super("Bone Rush", 25, 90, Type.GROUND, Category.PHYSICAL, Range.NORMAL, 16, false, 0, 2, 5);
    }
}
