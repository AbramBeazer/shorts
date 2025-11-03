package org.shorts.model.moves;

public interface BallBombMove extends IMove {

    @Override
    default boolean isBallBombMove() {
        return true;
    }
}
