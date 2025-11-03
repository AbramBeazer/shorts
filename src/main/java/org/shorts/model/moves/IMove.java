package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

public interface IMove {

    default boolean alwaysCrits() {
        return false;
    }

    default boolean alwaysHitsMinimize() {
        return false;
    }

    default boolean bypassesProtection(Pokemon target) {
        return false;
    }

    default boolean isSlicingMove() {
        return false;
    }
}
