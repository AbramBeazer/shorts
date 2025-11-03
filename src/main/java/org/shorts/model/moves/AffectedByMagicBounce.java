package org.shorts.model.moves;

public interface AffectedByMagicBounce extends IMove {

    @Override
    default boolean isAffectedByMagicBounce() {
        return true;
    }
}
