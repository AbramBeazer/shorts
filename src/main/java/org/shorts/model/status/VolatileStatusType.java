package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;

public enum VolatileStatusType implements AbstractStatusType {
    ABILITY_CHANGED, // TODO: Maybe I need to allow this one to take an ability as a member?
    ABILITY_SUPPRESSED,
    ABILITY_IGNORED,
    TYPE_CHANGE,
    MIMIC,
    SUBSTITUTE, //HP
    ILLUSION,
    BOUND,
    CURSED,
    NIGHTMARE,
    PERISH,
    SEEDED,
    SALT_CURE,
    //SPLINTERS, -- only in Legends: Arceus
    AUTOTOMIZED,
    IDENTIFIED,
    MINIMIZED,
    TARRED,
    GROUNDED,
    MAGNET_LEVITATION,
    TELEKINESIS,
    AQUA_RING,
    ROOTED,
    LASER_FOCUS,
    TAKE_AIM,
    USED_GLAIVE_RUSH,
    DROWSY,
    CHARGED,
    STOCKPILE_1,
    STOCKPILE_2,
    STOCKPILE_3,
    DEFENSE_CURL,
    //PRIMED, -- only in Legends: Arceus
    CANT_ESCAPE,
    NO_RETREAT,
    OCTOLOCKED,
    DISABLED, //move
    EMBARGOED,
    HEAL_BLOCKED,
    IMPRISONED,
    TAUNTED,
    THROAT_CHOPPED,
    TORMENTED, //move
    CONFUSED,
    INFATUATED,
    PUMPED,
    GUARD_SPLIT,
    POWER_SPLIT,
    SPEED_SWAP,
    POWER_TRICK,
    //POWER_BOOST, -- only in Legends: Arceus
    //POWER_DROP, -- only in Legends: Arceus
    //GUARD_BOOST, -- only in Legends: Arceus
    //GUARD_DROP, -- only in Legends: Arceus
    CHOICE_LOCKED,
    ENCORED,
    RAMPAGING,
    ROLLING,
    MAKING_AN_UPROAR,
    //FIXATED, -- only in Legends: Arceus
    BIDING,
    MUST_RECHARGE,
    CHARGING_MOVE, //move
    SEMI_INVULNERABLE, //move
    FLINCH,
    BRACING,
    CENTER_OF_ATTENTION,
    MAGIC_COAT, // Applies to Magic Bounce -- Should I just make Magic Bounce bestow this condition?
    PROTECTED,
    HELPING_HAND,
    MICLE_BERRY_EFFECT,
    SYRUP_BOMBED;

    @Override
    public boolean isStatusPossible(Pokemon target, Battle battle) {
        //TODO: Should the volatile status be applied if the target already has it?
        if (!(target.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) target.getAbility()).getImmunities().contains(this))
            && !target.hasVolatileStatus(this)) {
            switch (this) {
                case DROWSY:
                    return battle.getCorrespondingTrainer(target).getSafeguardTurns() == 0;
                case CANT_ESCAPE:
                    return !target.hasVolatileStatus(SUBSTITUTE) && !target.hasVolatileStatus(NO_RETREAT)
                        && !target.hasVolatileStatus(OCTOLOCKED);
                case OCTOLOCKED:
                    return !target.hasVolatileStatus(SUBSTITUTE);
                case FLINCH:
                    return !target.hasVolatileStatus(SUBSTITUTE);
                case CONFUSED:
                    return !target.hasVolatileStatus(SUBSTITUTE); //TODO: Make sure that this blocks only Confuse Ray, but not self-inflicted confusion from Outrage or Thrash.
                default:
                    return true;
            }
        }
        return false;
    }

}
