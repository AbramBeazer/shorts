package org.shorts.model.moves.healthdependent;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class DragonEnergy extends HealthDependentMove {

    public DragonEnergy() {
        super("Dragon Energy", 150, 100, Type.DRAGON, Move.Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 8, false, 0);
    }
}
