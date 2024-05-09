package org.shorts.model.status;

public class Status extends AbstractStatus {

    private StatusType type;

    protected Status(StatusType type, int turnsRemaining) {
        super(turnsRemaining);
        this.type = type;
    }

    public StatusType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Status && this.type.equals(((Status) obj).type);
    }

    public static Status newBurn() {
        return new Status(StatusType.BURN, -1);
    }

    public static Status newPoison() {
        return new Status(StatusType.POISON, -1);
    }

    public static Status newToxic() {
        return new Status(StatusType.TOXIC_POISON, -1);
    }

    public static Status newParalyze() {
        return new Status(StatusType.PARALYZE, -1);
    }

    public static Status newSleep(int turnsRemaining) {
        return new Status(StatusType.SLEEP, turnsRemaining);
    }

    public static Status newFreeze() {
        return new Status(StatusType.FREEZE, -1);
    }

    public static final Status NONE = new Status(StatusType.NONE, -1);

    public enum StatusType implements AbstractStatusType {
        NONE,
        SLEEP,
        BURN,
        FREEZE,
        PARALYZE,
        POISON,
        TOXIC_POISON,
        FAINT
    }
}