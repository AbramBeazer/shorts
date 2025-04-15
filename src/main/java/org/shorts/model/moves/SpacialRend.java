package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SpacialRend extends Move implements HighCritChanceMove {

    public SpacialRend() {
        super("Spacial Rend", 100, 95, Type.DRAGON, Category.SPECIAL, Range.NORMAL, 8, false, 0);
    }
}
