package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Megahorn extends Move {

    public Megahorn() {
        super("Megahorn", 120, 85, Type.BUG, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, true, 0);
    }
}
