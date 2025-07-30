package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Surf extends Move implements CanHitDive {

    //TODO: Verify power, PP, etc.
    public Surf() {
        super("Surf", 90, 100, Type.WATER, Category.SPECIAL, Range.ALL_ADJACENT, 24, false, 0);
    }

}
