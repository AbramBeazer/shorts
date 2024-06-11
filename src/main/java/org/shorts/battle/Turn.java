package org.shorts.battle;

import java.util.List;

import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class Turn {

    private final Pokemon user;
    private final Move move;
    private final int singleTargetIndex;

    public Turn(Pokemon user, Move move, int singleTargetIndex) {
        this.user = user;
        this.move = move;
        this.singleTargetIndex = singleTargetIndex;
    }

    public Turn(Pokemon user, Move move) {
        this(user, move, -1);
    }

    public Pokemon getUser() {
        return user;
    }

    public Move getMove() {
        return move;
    }

    public int getSingleTargetIndex() {
        return singleTargetIndex;
    }

    public void takeTurn(Battle battle) {
        final int activeMonsPerSide = battle.getActiveMonsPerSide();
        final Trainer player = battle.getCorrespondingTrainer(user);
        final Trainer opponent = battle.getOpposingTrainer(user);
        if (this.move != null) {
            final List<Pokemon> targets;
            if (singleTargetIndex >= 0) {
                targets = List.of(singleTargetIndex < activeMonsPerSide
                    ? opponent.getActivePokemon().get(singleTargetIndex)
                    : player.getActivePokemon().get(singleTargetIndex - activeMonsPerSide));
            } else {
                targets = battle.getPokemonWithinRange(user, move.getRange(user));
            }
            move.execute(user, targets, battle);
        } else {
            //TODO: Switch
        }
    }
}
