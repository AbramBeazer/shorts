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

    default boolean isPunchingMove() {
        return false;
    }

    default boolean isKickingMove() {
        return false;
    }

    default boolean isBitingMove() {
        return false;
    }

    default boolean isBallBombMove() {
        return false;
    }

    default boolean isWindMove() {
        return false;
    }

    default boolean getsSheerForceBoost() {
        return false;
    }

    default boolean isAffectedByMagicBounce() {
        return false;
    }

    default boolean setsEntryHazards() {
        return false;
    }
}
