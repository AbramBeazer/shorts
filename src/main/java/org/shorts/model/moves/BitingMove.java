package org.shorts.model.moves;

public interface BitingMove extends IMove {

    @Override
    default boolean isBitingMove() {
        return true;
    }
}
