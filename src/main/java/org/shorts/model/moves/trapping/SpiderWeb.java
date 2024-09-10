package org.shorts.model.moves.trapping;

import org.shorts.model.moves.AffectedByMagicBounce;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SpiderWeb extends TrappingMove implements AffectedByMagicBounce {

    public SpiderWeb() {
        super("Spider Web", 0, -1, Type.BUG, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }
}
