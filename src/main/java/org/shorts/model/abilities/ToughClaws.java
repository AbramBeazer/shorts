package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class ToughClaws extends Ability {

    private ToughClaws() {
        super("Tough Claws");
    }

    public static final ToughClaws TOUGH_CLAWS = new ToughClaws();

    public static final double MULTIPLIER = 1.5;

    @Override
    public double getMovePowerMultipliers(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        return move.isContact(self) ? MULTIPLIER : 1;
    }
}
