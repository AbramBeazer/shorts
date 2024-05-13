package org.shorts.model.status;

public class SubstituteStatus extends VolatileStatus {

    private int subHP;

    public SubstituteStatus(int subHP) {
        super(VolatileStatusType.SUBSTITUTE, -1);
        this.subHP = subHP;
    }

    public int getSubHP() {
        return subHP;
    }

    public void takeDamage(int damage) {
        this.subHP = Math.max(this.subHP - damage, 0);
    }
}
