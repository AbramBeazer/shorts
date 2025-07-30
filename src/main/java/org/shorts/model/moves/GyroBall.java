package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class GyroBall extends Move implements BallBombMove {

    public GyroBall() {
        super("Gyro Ball", 25, 100, Type.STEEL, Category.PHYSICAL, Range.NORMAL, 8, true, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        final double gyroBallPower = (25d * target.calculateSpeed(battle) / user.calculateSpeed(battle)) + 1;
        return Math.min(150, gyroBallPower);
    }
}
