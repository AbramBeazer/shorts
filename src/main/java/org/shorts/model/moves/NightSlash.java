package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class NightSlash extends Move implements SlicingMove, HighCritChanceMove {

    public NightSlash() {
        super("Night Slash", 70, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }
}
