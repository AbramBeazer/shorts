package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Barrage extends MultiHitMove {

    public Barrage() {
        super("Barrage", 15, 85, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, false, 0, 2, 5);
    }
}