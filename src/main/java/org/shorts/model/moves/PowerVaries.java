package org.shorts.model.moves;

public interface PowerVaries extends IMove {

    @Override
    default boolean getsTera60Boost() {
        return false;
    }
}
