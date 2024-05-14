package org.shorts.model.status;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class SubstituteStatus extends VolatileStatus {

    private int subHP;

    public SubstituteStatus(int subHP) {
        super(SUBSTITUTE, -1);
        this.subHP = subHP;
    }

    public int getSubHP() {
        return subHP;
    }

    public void takeDamage(int damage) {
        this.subHP = Math.max(this.subHP - damage, 0);
    }
}
