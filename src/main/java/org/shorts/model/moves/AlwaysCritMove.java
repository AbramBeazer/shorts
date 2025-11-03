package org.shorts.model.moves;

public interface AlwaysCritMove extends IMove {

    @Override
    default boolean alwaysCrits() {
        return true;
    }
}
