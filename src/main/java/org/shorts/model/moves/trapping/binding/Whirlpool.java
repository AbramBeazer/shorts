package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Whirlpool extends BindingMove {

    public Whirlpool() {
        super("Whirlpool", 35, 85, Type.WATER, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }
}
