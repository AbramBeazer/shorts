package org.shorts.model.moves.trapping;

import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class AnchorShot extends TrappingMove implements GetsSheerForceBoost {

    public AnchorShot() {
        super("Anchor Shot", 80, 100, Type.STEEL, Category.PHYSICAL, Range.NORMAL, 32, true, 100);
    }

}
