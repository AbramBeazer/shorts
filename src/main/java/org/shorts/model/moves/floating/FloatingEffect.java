package org.shorts.model.moves.floating;

import org.shorts.model.pokemon.Pokemon;

public class FloatingEffect {

    private FloatingMove move;
    private Pokemon user;
    private int targetIndex;
    private int turnsRemaining;

    public FloatingMove getMove() {
        return move;
    }

    public Pokemon getUser() {
        return user;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public void decrementTurnsRemaining() {
        turnsRemaining--;
    }

    public FloatingEffect(FloatingMove move, Pokemon user, int targetIndex, int turnsRemaining) {
        this.move = move;
        this.user = user;
        this.targetIndex = targetIndex;
        this.turnsRemaining = turnsRemaining;
    }
}
