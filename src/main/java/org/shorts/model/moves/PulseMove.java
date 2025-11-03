package org.shorts.model.moves;

public interface PulseMove extends IMove {

    @Override
    default boolean isPulseMove() {
        return true;
    }
}
