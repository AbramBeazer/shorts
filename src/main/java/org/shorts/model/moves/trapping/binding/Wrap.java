package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Wrap extends BindingMove {

    public Wrap() {
        super("Wrap", 15, 90, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 100);
    }
}
