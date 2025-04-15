package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Cut extends Move implements SlicingMove {

    public Cut() {
        super("Cut", 50, 95, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 48, true, 0);
    }
}
