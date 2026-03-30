package org.shorts.model.moves;

public interface PowerVaries extends IMove {

    @Override
    default boolean hasMinPower60WhenTera() {
        return false;
    }
}
