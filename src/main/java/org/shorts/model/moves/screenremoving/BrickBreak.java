package org.shorts.model.moves.screenremoving;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class BrickBreak extends ScreenRemovingMove {

    public BrickBreak() {
        super("Brick Break", 75, 100, Type.FIGHTING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 0);
    }
}
