package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class WickedBlow extends Move implements AlwaysCritMove {

    public WickedBlow() {
        super("Wicked Blow", 75, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, true, 0);
    }
}
