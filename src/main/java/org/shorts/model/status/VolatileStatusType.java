package org.shorts.model.status;

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
    PROTECTED;

}
