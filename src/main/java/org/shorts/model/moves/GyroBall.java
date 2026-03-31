package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class GyroBall extends Move implements BallBombMove, SpeedComparisonMove {

    protected static final int BASE_POWER = 25;
    protected static final int MAX_POWER = 150;

    public GyroBall() {
        super("Gyro Ball", BASE_POWER, 100, Type.STEEL, Category.PHYSICAL, Range.NORMAL, 8, true, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        final double gyroBallPower = (BASE_POWER * target.calculateSpeed(battle) / user.calculateSpeed(battle)) + 1;
        return Math.min(MAX_POWER, gyroBallPower);
    }
}
