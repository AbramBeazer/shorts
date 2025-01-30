package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Bind extends BindingMove {

    public Bind() {
        super("Bind", 15, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 100);
    }
}
