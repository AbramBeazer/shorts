package org.shorts.model.status;

public abstract class AbstractStatus {

    int turnsRemaining;

    protected AbstractStatus(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public void decrementTurns() {
        if (turnsRemaining > 0) {
            turnsRemaining--;
        }
    }
}
