package org.shorts.model.moves;

public interface GetsSheerForceBoost extends IMove {

    @Override
    default boolean getsSheerForceBoost() {
        return true;
    }
}
