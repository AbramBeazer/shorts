package org.shorts.model.status;

import org.shorts.model.moves.Move;

public class BoundStatus extends VolatileStatus {
    private double damage;

    public BoundStatus(int turnsRemaining, Move move, double damage) {
        super(VolatileStatusType.BOUND, turnsRemaining, move);
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
