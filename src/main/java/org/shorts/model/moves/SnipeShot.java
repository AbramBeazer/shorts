package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SnipeShot extends Move implements HighCritChanceMove, CantBeDrawnAwayMove {

    public SnipeShot() {
        super("Snipe Shot", 80, 100, Type.WATER, Category.SPECIAL, Range.NORMAL, 24, false, 0);
    }
}
