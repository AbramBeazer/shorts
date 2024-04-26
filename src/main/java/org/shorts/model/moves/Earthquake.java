package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Earthquake extends PhysicalMove {

    public Earthquake() {
        super("Earthquake", 100, 100, Type.GROUND, 16, false, 0);
    }

    @Override
    public double getMultiplier(Pokemon attacker, Pokemon defender, Battle battle) throws Exception {
        double multiplier = super.getMultiplier(attacker, defender, battle);
        if (defender.isUsingDig()) {
            multiplier *= 2;
        }
        if (battle.getTerrainType().getName().equals("Grassy")) {
            multiplier *= 0.5;
        }
        return multiplier;
    }

    public static final Earthquake EARTHQUAKE = new Earthquake();
}
