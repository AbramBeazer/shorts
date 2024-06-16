package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MotorDrive extends Ability {

    public MotorDrive() {
        super("Motor Drive");
    }

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(Type.ELECTRIC)) {
            self.changeSpeed(1);
            return 0;
        }
        return 1;
    }
}
