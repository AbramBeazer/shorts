package org.shorts.model.moves;

public interface DoubleDamageToFly extends CanHitFly {

    @Override
    default boolean doubleDamageToFly() {
        return true;
    }
}
