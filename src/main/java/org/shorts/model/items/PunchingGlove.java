package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PunchingMove;
import org.shorts.model.pokemon.Pokemon;

public class PunchingGlove extends HeldItem {

    private PunchingGlove() {
        super("Punching Glove", 30);
    }

    public static final PunchingGlove PUNCHING_GLOVE = new PunchingGlove();

    public static final double MULTIPLIER = 1.1;

    @Override
    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        final double base = super.getMovePowerMultipliers(user, opponent, battle, move);
        return move instanceof PunchingMove ? base * MULTIPLIER : base;
    }
}
