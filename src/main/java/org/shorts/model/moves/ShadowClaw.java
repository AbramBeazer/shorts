package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class ShadowClaw extends Move implements HighCritChanceMove {

    public ShadowClaw() {
        super("Shadow Claw", 70, 100, Type.GHOST, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 0);
    }
}
