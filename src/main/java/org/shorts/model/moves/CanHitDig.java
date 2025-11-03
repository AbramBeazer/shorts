package org.shorts.model.moves;

public interface CanHitDig extends IMove {

    @Override
    default boolean canHitDig() {
        return true;
    }
}
