package org.shorts.model.moves;

public interface HitsMinimize extends IMove {

    @Override
    default boolean alwaysHitsMinimize() {
        return true;
    }
}
