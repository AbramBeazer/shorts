package org.shorts.model.moves.trapping;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FairyLock extends TrappingMove {

    public FairyLock() {
        super("Fairy Lock", 0, -1, Type.FAIRY, Category.STATUS, Range.BOTH_SIDES, 16, false, 100);
    }

}
