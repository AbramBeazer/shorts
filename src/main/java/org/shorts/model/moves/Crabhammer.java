package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Crabhammer extends Move implements HighCritChanceMove {

    public Crabhammer() {
        super("Crabhammer", 100, 90, Type.WATER, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, true, 0);
    }
}
