package org.shorts.model.status.volatilestatus;

import org.shorts.model.moves.Move;
import org.shorts.model.status.AbstractStatus;
import org.shorts.model.status.AbstractStatusType;

public class VolatileStatus extends AbstractStatus {

    VolatileStatusType type;
    Move move;

    public VolatileStatus(VolatileStatusType type, int turnsRemaining) {
        super(turnsRemaining);
        this.type = type;
        this.move = null;
    }

    public VolatileStatus(VolatileStatusType type, int turnsRemaining, Move move) {
        super(turnsRemaining);
        this.type = type;
        this.move = move;
    }

    public VolatileStatusType getType() {
        return type;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VolatileStatus && this.type.equals(((VolatileStatus) obj).type);
    }

    public enum VolatileStatusType implements AbstractStatusType {
        ABILITY_CHANGE,
        ABILITY_SUPPRESSION,
        TYPE_CHANGE,
        MIMIC,
        SUBSTITUTE,
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
        DISABLED,//move
        EMBARGOED,
        HEAL_BLOCKED,
        IMPRISONED,
        TAUNTED,
        THROAT_CHOPPED,
        TORMENTED,//move
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
        CHARGING_MOVE,//move
        SEMI_INVULNERABLE,//move
        FLINCH,
        BRACING,
        CENTER_OF_ATTENTION,
        MAGIC_COAT, // Applies to Magic Bounce -- Should I just make Magic Bounce bestow this condition?
        PROTECTED;
    }
}

