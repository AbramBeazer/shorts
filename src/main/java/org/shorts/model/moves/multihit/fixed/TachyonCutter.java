package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.BoostedBySharpness;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class TachyonCutter extends FixedMultiHitMove implements BoostedBySharpness {

    public TachyonCutter() {
        super("Tachyon Cutter", 50, -1, Type.STEEL, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0, 2);
    }
}
