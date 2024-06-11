package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class StoneEdge extends Move implements HighCritChanceMove {

    public StoneEdge() {
        super("Stone Edge", 100, 80, Type.ROCK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, false, 0);
    }

}
