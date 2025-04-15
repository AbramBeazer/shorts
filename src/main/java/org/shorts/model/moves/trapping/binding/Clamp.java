package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class Clamp extends BindingMove {

    public Clamp() {
        super("Clamp", 35, 85, Type.WATER, Category.PHYSICAL, Range.NORMAL, 24, true, 100);
    }
}
