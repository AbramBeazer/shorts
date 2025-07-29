package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Gust extends Move implements WindMove {

    public Gust() {
        super("Gust", 40, 100, Type.FLYING, Category.SPECIAL, Range.SINGLE_ANY, 56, false, 0);
    }
}
