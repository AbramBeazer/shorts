package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

public interface IgnoresProtection extends IMove {

    @Override
    default boolean bypassesProtection(Pokemon target) {
        return true;
    }
}
