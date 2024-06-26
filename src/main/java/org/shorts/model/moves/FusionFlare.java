package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class FusionFlare extends Move implements SelfThawingMove {

    public FusionFlare() {
        super("Fusion Flare", 100, 100, Type.FIRE, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 8, false, 100);
    }

    //TODO: Implement -- this needs to double in power if Fusion Bolt was the last move successfully used in battle, and should make it so a subsequent Fusion Bolt has double power.
}
