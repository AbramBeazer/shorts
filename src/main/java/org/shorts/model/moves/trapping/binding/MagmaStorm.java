package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class MagmaStorm extends BindingMove {

    public MagmaStorm() {
        super("Magma Storm", 100, 75, Type.FIRE, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, false, 100);
    }
}
