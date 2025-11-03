package org.shorts.model.moves;

public interface PunchingMove extends IMove {

    @Override
    default boolean isPunchingMove() {
        return true;
    }
}
