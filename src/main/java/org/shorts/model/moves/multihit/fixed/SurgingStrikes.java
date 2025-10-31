package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.AlwaysCritMove;
import org.shorts.model.moves.PunchingMove;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SurgingStrikes extends FixedMultiHitMove implements AlwaysCritMove, PunchingMove {

    public SurgingStrikes() {
        super("Surging Strikes", 25, 100, Type.WATER, Category.PHYSICAL, Range.NORMAL, 8, true, 0, 2);
    }
}
