package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SolarBlade extends Move implements SlicingMove, MultiTurnMove {

    public SolarBlade() {
        super("Solar Blade", 125, 100, Type.GRASS, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    //TODO: Implement moves that have a charging turn or semi-invulnerable turn.
}
