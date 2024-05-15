package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

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
    public boolean isStatusPossible(Pokemon target, Battle battle) {
        if (!(target.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) target.getAbility()).getImmunities().contains(this.getType()))) {
            switch (type) {
                case CANT_ESCAPE:
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

    public static final VolatileStatus INFATUATED = new VolatileStatus(VolatileStatusType.INFATUATED, -1);
    public static final VolatileStatus CURSED = new VolatileStatus(VolatileStatusType.CURSED, -1);
    public static final VolatileStatus ABILITY_SUPPRESSED = new VolatileStatus(
        VolatileStatusType.ABILITY_SUPPRESSED,
        -1);
    public static final VolatileStatus ABILITY_IGNORED = new VolatileStatus(VolatileStatusType.ABILITY_IGNORED, -1);
    public static final VolatileStatus MAGIC_COAT = new VolatileStatus(VolatileStatusType.MAGIC_COAT, -1);
    public static final VolatileStatus CANT_ESCAPE = new VolatileStatus(VolatileStatusType.CANT_ESCAPE, -1);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VolatileStatus && this.type.equals(((VolatileStatus) obj).type);
    }

    @Override
    public int hashCode() {
        return 151 * (type.ordinal() + 1) * turnsRemaining * (move == null ? 11 : move.hashCode());
    }

}

