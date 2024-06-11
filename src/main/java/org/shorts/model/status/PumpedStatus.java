package org.shorts.model.status;

public class PumpedStatus extends VolatileStatus {

    private final int levels;

    public PumpedStatus(int levels) {
        super(VolatileStatusType.PUMPED, -1);
        this.levels = levels;
    }

    public int getLevels() {
        return levels;
    }
}
