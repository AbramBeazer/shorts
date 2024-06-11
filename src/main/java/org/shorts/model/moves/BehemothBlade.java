package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class BehemothBlade extends Move implements SlicingMove {

    public BehemothBlade() {
        super("Behemoth Blade", 100, 100, Type.STEEL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 0);
    }
}
