package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FireSpin extends BindingMove {

    public FireSpin() {
        super("Fire Spin", 15, 85, Type.FIRE, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }
}
