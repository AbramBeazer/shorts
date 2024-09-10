package org.shorts.model.status;

public class AutotomizedStatus extends VolatileStatus {

    private int levels;

    public AutotomizedStatus() {
        super(VolatileStatusType.AUTOTOMIZED, -1);
        this.levels = 1;
    }

    public int getLevels() {
        return levels;
    }

    public void incrementLevels() {
        levels++;
    }
}
