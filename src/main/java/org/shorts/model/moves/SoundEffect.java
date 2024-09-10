package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.Soundproof.SOUNDPROOF;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;

public interface SoundEffect {

    default boolean soundproofApplies(Pokemon target) {
        return target.getAbility() == SOUNDPROOF
            && !target.hasVolatileStatus(ABILITY_IGNORED) && !target.hasVolatileStatus(ABILITY_SUPPRESSED);
    }
}
