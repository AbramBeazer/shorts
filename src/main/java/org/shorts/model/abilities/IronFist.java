package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.PunchingMove;
import org.shorts.model.pokemon.Pokemon;

public class IronFist extends Ability {

    private IronFist() {
        super("IronFist");
    }

    public static final IronFist IRON_FIST = new IronFist();

    public static final double MULTIPLIER = 4915d / 4096d;

    @Override
    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        final double base = super.getMovePowerMultipliers(user, opponent, battle, move);
        return move instanceof PunchingMove ? base * MULTIPLIER : base;
    }
}
