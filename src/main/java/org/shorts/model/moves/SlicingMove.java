package org.shorts.model.moves;

public interface SlicingMove extends IMove {

    @Override
    default boolean isSlicingMove() {
        return true;
    }
}
