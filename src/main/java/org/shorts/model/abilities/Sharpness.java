package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.SlicingMove;
import org.shorts.model.pokemon.Pokemon;

public class Sharpness extends Ability {

    private static final double SHARPNESS_BOOST = 1.5;

    private Sharpness() {
        super("Sharpness");
    }

    public static final Sharpness SHARPNESS = new Sharpness();

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return move instanceof SlicingMove ? SHARPNESS_BOOST : 1;
    }
}
