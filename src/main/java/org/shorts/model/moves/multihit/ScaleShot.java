package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class ScaleShot extends MultiHitMove {

    public ScaleShot() {
        super("Scale Shot", 25, 90, Type.DRAGON, Category.PHYSICAL, Range.NORMAL, 32, false, 0, 2, 5);
    }
}
