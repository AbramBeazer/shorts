package org.shorts.model.moves.trapping;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

public interface TrappingMove {

    default void applyCantEscapeStatus(Pokemon pokemon) {
        pokemon.addVolatileStatus(VolatileStatus.CANT_ESCAPE);
    }
}
