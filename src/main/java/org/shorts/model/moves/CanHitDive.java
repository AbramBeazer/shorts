package org.shorts.model.moves;

public interface CanHitDive extends IMove {

    @Override
    default boolean canHitDive() {
        return true;
    }
}
