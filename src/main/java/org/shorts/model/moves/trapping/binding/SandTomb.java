package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class SandTomb extends BindingMove {

    public SandTomb() {
        super("Sand Tomb", 35, 85, Type.GROUND, Category.PHYSICAL, Range.NORMAL, 24, false, 100);
    }
}
