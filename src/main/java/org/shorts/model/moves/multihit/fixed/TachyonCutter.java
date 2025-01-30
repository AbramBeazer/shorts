package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.moves.SlicingMove;
import org.shorts.model.types.Type;

public class TachyonCutter extends FixedMultiHitMove implements SlicingMove {

    public TachyonCutter() {
        super("Tachyon Cutter", 50, -1, Type.STEEL, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 0, 2);
    }
}
