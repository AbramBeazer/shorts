package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ElectroBall extends Move implements BallBombMove {

    public ElectroBall() {
        super("Electro Ball", 40, 100, Type.ELECTRIC, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        double percentage = target.calculateSpeed(battle) / user.calculateSpeed(battle);

        if (percentage > 1) {
            return 1;
        } else if (percentage > 0.5) {
            return 1.5;
        } else if (percentage > 1 / 3d) {
            return 2;
        } else if (percentage > 0.25) {
            return 3;
        } else {
            return 3.75;
        }
    }
}
