package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class StormThrow extends Move implements AlwaysCritMove {

    public StormThrow() {
        super("Storm Throw", 60, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }
}
