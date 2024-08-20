package org.shorts.model.moves.stealberry;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BugBite extends BerryStealingMove {

    public BugBite() {
        super("Bug Bite", 60, 100, Type.BUG, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 100);
    }
}
