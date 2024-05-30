package org.shorts.model.status;

import org.shorts.model.moves.Move;

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

    @Override
    public VolatileStatusType getType() {
        return type;
    }

    public Move getMove() {
        return move;
    }

    public static final VolatileStatus INFATUATED = new VolatileStatus(VolatileStatusType.INFATUATED, -1);
    public static final VolatileStatus CURSED = new VolatileStatus(VolatileStatusType.CURSED, -1);
    public static final VolatileStatus ABILITY_SUPPRESSED = new VolatileStatus(
        VolatileStatusType.ABILITY_SUPPRESSED,
        -1);
    public static final VolatileStatus ABILITY_IGNORED = new VolatileStatus(VolatileStatusType.ABILITY_IGNORED, -1);
    public static final VolatileStatus CANT_ESCAPE_INDEFINITE = new VolatileStatus(VolatileStatusType.CANT_ESCAPE, -1);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VolatileStatus && this.type.equals(((VolatileStatus) obj).type);
    }

    @Override
    public int hashCode() {
        return 151 * (type.ordinal() + 1) * turnsRemaining * (move == null ? 11 : move.hashCode());
    }

}

