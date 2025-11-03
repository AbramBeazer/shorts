package org.shorts.model.moves;

public interface WindMove extends IMove {

    @Override
    default boolean isWindMove() {
        return true;
    }
}
