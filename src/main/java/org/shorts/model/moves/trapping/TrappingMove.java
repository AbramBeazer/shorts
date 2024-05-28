package org.shorts.model.moves.trapping;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

public interface TrappingMove {

    default boolean targetIsNotGhost(Pokemon target) {
        return !target.getTypes().contains(Type.GHOST);
    }

    default void applyCantEscapeStatus(Pokemon pokemon) {
        pokemon.addVolatileStatus(VolatileStatus.CANT_ESCAPE);
    }
}
