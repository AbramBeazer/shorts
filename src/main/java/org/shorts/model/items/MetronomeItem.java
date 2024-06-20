package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class MetronomeItem extends HeldItem {

    private Move move;
    private int previousUses = 0;

    //TODO: What happens if this item is given by Trick or Fling? Does the boost retroactively apply?

    public MetronomeItem() {
        super("Metronome", 30);
    }

    @Override
    public void afterAttack(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        if (move != null) {
            if (!this.move.equals(user.getLastMoveUsed())) {
                this.previousUses = 0;
            }
            this.move = user.getLastMoveUsed();
            if (this.previousUses < 5) {
                this.previousUses++;
            }
        }
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getPreviousUses() {
        return previousUses;
    }
}
