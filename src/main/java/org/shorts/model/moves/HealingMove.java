package org.shorts.model.moves;

public interface HealingMove extends IMove {

    @Override
    default boolean isHealingMove() {
        return true;
    }
}
