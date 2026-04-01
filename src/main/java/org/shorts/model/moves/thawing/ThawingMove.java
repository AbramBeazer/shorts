package org.shorts.model.moves.thawing;

import org.shorts.model.moves.IMove;

public interface ThawingMove extends IMove {

    @Override
    default boolean isThawingMove() {
        return true;
    }
}
