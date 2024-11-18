package org.shorts.model.status;

import org.shorts.Main;

public class Status extends AbstractStatus {

    private final StatusType type;

    Status(StatusType type, int turnsRemaining) {
        super(turnsRemaining);
        this.type = type;
    }

    @Override
    public StatusType getType() {
        return type;
    }

    //to be used in most cases
    public static Status createSleep() {
        return new Status(StatusType.SLEEP, Main.RANDOM.nextInt(3) + 1);
    }

    //to be used for Rest and other cases where the sleep lasts a set number of turns.
    public static Status createSleepForTurns(int turnsRemaining) {
        return new Status(StatusType.SLEEP, turnsRemaining);
    }

    public static Status createToxic() {
        return new Status(StatusType.TOXIC_POISON, -1);
    }

    public static final Status NONE = new Status(StatusType.NONE, -1);
    public static final Status PARALYZE = new Status(StatusType.PARALYZE, -1);
    public static final Status BURN = new Status(StatusType.BURN, -1);
    public static final Status FREEZE = new Status(StatusType.FREEZE, -1);
    public static final Status POISON = new Status(StatusType.POISON, -1);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Status && this.type.equals(((Status) obj).type);
    }

    @Override
    public int hashCode() {
        return 151 * (type.ordinal() + 1) * Math.abs(turnsRemaining);
    }

}