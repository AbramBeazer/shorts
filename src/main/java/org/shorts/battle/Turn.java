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

    public int getPriority(Battle battle) {
        if (move == null) {
            return 6;
        } else {
            return move.getPriority(user, battle) + move.getAbilityPriorityBonus(user);
        }
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
            System.out.println(user.getDisplayName() + " used " + move.getName() + "!");
            move.execute(user, targets, battle);
        } else {
            System.out.println(player.getName() + " recalled " + user.getDisplayName() + "!");
            final int index = player.getTeam().indexOf(user);
            player.switchPokemon(index, singleTargetIndex - 4);
            System.out.println(player.getName() + " sent out " + player.getTeam().get(index).getDisplayName() + "!");
        }
    }
}
