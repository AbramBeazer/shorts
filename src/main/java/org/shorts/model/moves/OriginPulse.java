package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class OriginPulse extends Move implements PulseEffect {

    public OriginPulse() {
        super("Origin Pulse", 110, 85, Type.WATER, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 16, false, 0);
    }
}
