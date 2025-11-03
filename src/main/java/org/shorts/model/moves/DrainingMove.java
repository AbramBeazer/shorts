package org.shorts.model.moves;

public interface DrainingMove extends IMove {

    @Override
    default boolean isDrainingMove() {
        return true;
    }
}
