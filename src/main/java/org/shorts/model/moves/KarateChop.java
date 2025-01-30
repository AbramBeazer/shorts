package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class KarateChop extends Move implements HighCritChanceMove {

    public KarateChop() {
        super("Karate Chop", 50, 100, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 40, true, 0);
    }

    //Not in Gen 8 or 9.
}

