package org.shorts.model.moves.trapping;

import org.shorts.model.moves.AffectedByMagicBounce;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class MeanLook extends TrappingMove implements AffectedByMagicBounce {

    public MeanLook() {
        super("Mean Look", 0, -1, Type.NORMAL, Category.STATUS, Range.NORMAL, 8, false, 100);
    }
}
