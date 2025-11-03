package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class AuraSphere extends Move implements PulseMove, BallBombMove {

    public AuraSphere() {
        super("Aura Sphere", 80, -1, Type.FIGHTING, Category.SPECIAL, Range.SINGLE_ANY, 32, false, 0);
    }
}
