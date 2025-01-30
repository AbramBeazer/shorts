package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class IcicleSpear extends MultiHitMove {

    public IcicleSpear() {
        super("Icicle Spear", 25, 100, Type.ICE, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 48, false, 0, 2, 5);
    }
}
