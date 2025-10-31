package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class ShadowPunch extends Move implements PunchingMove{
    public ShadowPunch() {
        super("Shadow Punch", 60, -1, Type.GHOST, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }
}
