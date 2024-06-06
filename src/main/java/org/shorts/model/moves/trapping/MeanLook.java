package org.shorts.model.moves.trapping;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class MeanLook extends TrappingMove {

    public MeanLook() {
        super("Mean Look", 0, -1, Type.NORMAL, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 8, false, 100);
    }
}
