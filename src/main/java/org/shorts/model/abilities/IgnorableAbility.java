package org.shorts.model.abilities;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

public interface IgnorableAbility {

    default boolean isIgnored(Pokemon pokemon) {
        return pokemon.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED);
    }
}
