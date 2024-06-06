package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class IvyCudgel extends Move implements HighCritChanceMove {

    public IvyCudgel() {
        super("Ivy Cudgel", 100, 100, Type.GRASS, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0);
    }

    //TODO: Make it change type based on Ogerpon's mask. Maybe this is something I can do in the masks' onGainHeldItem method.
}
