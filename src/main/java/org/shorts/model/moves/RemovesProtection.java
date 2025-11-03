package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.status.VolatileStatusType.*;

public interface RemovesProtection extends IMove {
    //TODO: Add moves

    @Override
    default boolean bypassesProtection(Pokemon target) {
        target.removeVolatileStatus(PROTECTED);
        return true;
    }
}
