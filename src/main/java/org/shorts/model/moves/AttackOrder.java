package org.shorts.model.moves;

import static org.shorts.model.types.Type.BUG;

public class AttackOrder extends Move implements HighCritChanceMove {

    public AttackOrder() {
        super("Attack Order", 90, 100, BUG, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, false, 0);
    }
}
