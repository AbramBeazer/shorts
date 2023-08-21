package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class NullMove extends Move {

    private NullMove() {
        super("", 0, 0, Type.NORMAL, Move.MoveGroup.STATUS, 0, false, Integer.MIN_VALUE);
    }

    public static final NullMove NULL_MOVE = new NullMove();

}
