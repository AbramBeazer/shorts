package org.shorts.model.moves.trapping;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class AnchorShot extends TrappingMove {

    public AnchorShot() {
        super("Anchor Shot", 80, 100, Type.STEEL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 100);
    }

}
