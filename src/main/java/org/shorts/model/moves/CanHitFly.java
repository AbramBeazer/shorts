package org.shorts.model.moves;

public interface CanHitFly extends IMove {

    @Override
    default boolean canHitFly() {
        return true;
    }
}
