package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class RazorWind extends Move implements HighCritChanceMove {

    public RazorWind() {
        super("Razor Wind", 80, 100, Type.NORMAL, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 16, false, 0);
    }

    //Not in Gen 8 or 9
}
