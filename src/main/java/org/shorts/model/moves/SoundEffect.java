package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.Soundproof.*;
import static org.shorts.model.status.VolatileStatusType.*;

public interface SoundEffect extends IMove {

    @Override
    default boolean isSoundEffect() {
        return true;
    }

    default boolean soundproofApplies(Pokemon target) {
        return target.getAbility() == SOUNDPROOF
            && !target.hasVolatileStatus(ABILITY_IGNORED) && !target.hasVolatileStatus(ABILITY_SUPPRESSED);
    }
}
// TODO:
//Sing
//Supersonic
//Snore
//Perish Song
//Heal Bell
//Uproar
//Hyper Voice
//Metal Sound
//Grass Whistle
//Bug Buzz
//Chatter
//Round
//Echoed Voice
//Relic Song
//Snarl
//Noble Roar
//Disarming Voice
//Parting Shot
//Boomburst
//Confide
//Sparkling Aria
//Clanging Scales
//Clangorous Soulblaze
//Clangorous Soul
//Overdrive
//Eerie Spell
//Torch Song
//Dragon Cheer*
//Alluring Voice
//Psychic Noise