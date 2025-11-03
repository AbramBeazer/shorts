package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.BitingMove;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class StrongJaw extends Ability {

    protected static final double MULTIPLIER = 1.5;

    private StrongJaw() {
        super("Strong Jaw");
    }

    public static final StrongJaw STRONG_JAW = new StrongJaw();

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return move.isBitingMove() ? MULTIPLIER : 1;
    }
}
