package org.shorts.model.moves.thawing;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FusionFlare extends ThawingMove {

    public FusionFlare() {
        super("Fusion Flare", 100, 100, Type.FIRE, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, false, 100);
    }

    //TODO: Implement -- this needs to double in power if Fusion Bolt was the last move successfully used in battle, and should make it so a subsequent Fusion Bolt has double power.
}
