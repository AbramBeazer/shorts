package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class TwinBeam extends FixedMultiHitMove {

    public TwinBeam() {
        super("Twin Beam", 40, 100, Type.PSYCHIC, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 0, 2);
    }
}
