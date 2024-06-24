package org.shorts.model.status;

public class AutotomizedStatus extends VolatileStatus {

    private int levels;

    public AutotomizedStatus() {
        super(VolatileStatusType.AUTOTOMIZED, -1);
        this.levels = levels;
    }

    public int getLevels() {
        return levels;
    }
}
