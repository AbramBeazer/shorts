package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class DragonClaw extends Move implements SlicingMove {

    public DragonClaw() {
        super("Dragon Claw", 80, 100, Type.DRAGON, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }
}
