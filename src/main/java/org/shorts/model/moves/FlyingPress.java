package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FlyingPress extends PhysicalMove {

    public FlyingPress() {
        super("Flying Press", 100, 100, Type.FIGHTING, 95, true, 0);
    }

    @Override
    public double getMultiplier(Pokemon attacker, Pokemon defender, Battle battle) throws Exception {
        double multiplier = super.getMultiplier(attacker, defender, battle);
        multiplier *= Type.getMultiplier(attacker.getTypes(), Type.FLYING, defender.getTypes());
        return multiplier;
    }
}
