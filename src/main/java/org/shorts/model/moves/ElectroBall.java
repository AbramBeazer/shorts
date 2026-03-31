package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ElectroBall extends Move implements BallBombMove, SpeedComparisonMove {

    public ElectroBall() {
        super("Electro Ball", 40, 100, Type.ELECTRIC, Category.SPECIAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        double percentage = target.calculateSpeed(battle) / user.calculateSpeed(battle);

        if (percentage > 1 || percentage == 0d) {
            return 40;
        } else if (percentage > 0.5) {
            return 60;
        } else if (percentage > 1 / 3d) {
            return 80;
        } else if (percentage > 0.25) {
            return 120;
        } else {
            return 150;
        }
    }
}
