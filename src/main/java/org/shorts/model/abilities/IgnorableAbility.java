package org.shorts.model.abilities;

import org.shorts.model.pokemon.Pokemon;

public interface IgnorableAbility {

    default boolean isIgnored(Pokemon pokemon) {
        return pokemon.isAbilityIgnored();
    }
}
