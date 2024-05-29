package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SnapTrap extends BindingMove {

    public SnapTrap() {
        super("Snap Trap", 35, 100, Type.GRASS, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 100);
    }

    //Not in Gen 9
}
