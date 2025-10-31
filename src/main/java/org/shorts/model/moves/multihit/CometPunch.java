package org.shorts.model.moves.multihit;

import org.shorts.model.moves.Range;
import org.shorts.model.moves.PunchingMove;
import org.shorts.model.types.Type;

public class CometPunch extends MultiHitMove implements PunchingMove {

    public CometPunch() {
        super("Comet Punch", 18, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 24, true, 0, 2, 5);
    }
}