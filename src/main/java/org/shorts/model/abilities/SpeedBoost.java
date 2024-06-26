package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

public class SpeedBoost extends Ability {

    private SpeedBoost() {
        super("Speed Boost");
    }

    public static final SpeedBoost SPEED_BOOST = new SpeedBoost();

    @Override
    public void afterTurn(Pokemon self, Battle battle) {
        self.changeStat(1, StatEnum.SPEED);
    }
}
