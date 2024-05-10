package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

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

    public abstract boolean isStatusPossible(Pokemon target, Battle battle);
}
