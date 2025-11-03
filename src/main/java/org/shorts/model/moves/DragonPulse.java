package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class DragonPulse extends Move implements PulseMove {

    public DragonPulse() {
        super("Dragon Pulse", 85, 100, Type.DRAGON, Category.SPECIAL, Range.SINGLE_ANY, 16, false, 0);
    }
}
