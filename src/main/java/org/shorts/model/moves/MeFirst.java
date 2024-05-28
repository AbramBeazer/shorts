package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class MeFirst extends Move {

    public MeFirst() {
        super("Me First", 0, -1, Type.NORMAL, Category.STATUS, Range.SINGLE_ADJACENT_OPPONENT, 32, false, 100);
    }
}
