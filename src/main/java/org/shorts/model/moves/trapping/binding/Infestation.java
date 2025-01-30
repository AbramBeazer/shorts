package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Infestation extends BindingMove {

    public Infestation() {
        super("Infestation", 20, 100, Type.BUG, Category.SPECIAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 100);
    }
}
