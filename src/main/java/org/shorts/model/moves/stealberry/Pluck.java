package org.shorts.model.moves.stealberry;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Pluck extends BerryStealingMove {

    public Pluck() {
        super("Pluck", 60, 100, Type.FLYING, Category.PHYSICAL, Range.SINGLE_ANY, 32, true, 100);
    }
}
