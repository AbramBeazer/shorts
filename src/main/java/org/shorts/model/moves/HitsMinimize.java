package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

public interface HitsMinimize {
    default boolean targetIsMinimized(Pokemon target) {
        return target.hasVolatileStatus(VolatileStatusType.MINIMIZED);
    }
}
