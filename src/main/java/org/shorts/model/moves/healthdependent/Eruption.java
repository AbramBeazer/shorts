package org.shorts.model.moves.healthdependent;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Eruption extends HealthDependentMove {

    public Eruption() {
        super("Eruption", 150, 100, Type.FIRE, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 8, false, 0);
    }
}
