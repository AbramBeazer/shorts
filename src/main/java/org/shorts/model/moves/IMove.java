package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

public interface IMove {

    default boolean alwaysCrits() {
        return false;
    }

    default boolean hasHighCritChance() {
        return false;
    }

    default boolean dealsExtraSuperEffectiveDamage() {
        return false;
    }

    default boolean alwaysHitsMinimize() {
        return false;
    }

    default boolean bypassesProtection(Pokemon target) {
        return false;
    }

    default boolean isHealingMove() {
        return false;
    }

    default boolean isDrainingMove() {
        return false;
    }

    default boolean isThawingMove() {
        return false;
    }

    default boolean isBitingMove() {
        return false;
    }

    default boolean isPunchingMove() {
        return false;
    }

    default boolean isKickingMove() {
        return false;
    }

    default boolean isBallBombMove() {
        return false;
    }

    default boolean isPulseMove() {
        return false;
    }

    default boolean isSlicingMove() {
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

    default boolean canHitDig() {
        return false;
    }

    default boolean canHitDive() {
        return false;
    }

    default boolean canHitFly() {
        return false;
    }

    default boolean doubleDamageToFly() {
        return false;
    }
}
