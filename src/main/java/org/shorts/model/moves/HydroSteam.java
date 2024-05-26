package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;

import static org.shorts.model.types.Type.WATER;

public class HydroSteam extends SpecialMove {

    public HydroSteam() {
        super("Hydro Steam", 80, 100, WATER, 24, false, 0);
    }

    @Override
    protected double getWeatherMultiplier(Battle battle) {
        if (!battle.isWeatherSuppressed()) {
            if ((battle.getWeather() == Weather.RAIN || battle.getWeather() == Weather.EXTREME_RAIN)
                && this.getType() == WATER) {
                return 1.5; //Checking if the move is still water-type may be unecessary, but you never know how move types may be affected by weird cases.
            } else if (battle.getWeather() == Weather.SUN || battle.getWeather() == Weather.EXTREME_SUN) {
                return 1.5;
            }
        }
        return 1;
    }
}

