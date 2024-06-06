package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class ThunderCage extends BindingMove {

    public ThunderCage() {
        super("Thunder Cage", 80, 90, Type.ELECTRIC, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 24, false, 100);
    }
}
