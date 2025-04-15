package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DoubleEdge extends RecoilAttack {

    public DoubleEdge() {
        super(
            "Double-Edge",
            120,
            100,
            Type.NORMAL,
            Category.PHYSICAL,
            Range.NORMAL,
            24,
            true,
            0,
            1 / 3d);
    }
}
