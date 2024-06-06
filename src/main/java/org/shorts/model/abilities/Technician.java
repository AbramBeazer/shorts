package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class Technician extends Ability {

    static final double MULTIPLIER = 1.5;
    static final int BASE_POWER_THRESHOLD = 60;

    private Technician() {
        super("Technician");
    }

    public static final Technician TECHNICIAN = new Technician();

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return move.getPower(self, opponent, battle) <= BASE_POWER_THRESHOLD ? MULTIPLIER : 1;
    }
}
