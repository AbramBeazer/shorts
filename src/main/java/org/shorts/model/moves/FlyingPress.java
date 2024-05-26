package org.shorts.model.moves;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FLYING;

public class FlyingPress extends PhysicalMove implements HitsMinimize {

    public FlyingPress() {
        super("Flying Press", 100, 100, FIGHTING, 95, true, 0);
    }

    @Override
    public double getBaseTypeMultiplier(Set<Type> defenderTypes) {
        return super.getBaseTypeMultiplier(defenderTypes) * Type.getTypeMultiplier(FLYING, defenderTypes);
    }

    @Override
    public boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        return HitsMinimize.super.targetIsMinimized(target) || super.rollToHit(user, target, battle);
    }

    @Override
    public double getOtherMultiplier(Pokemon user, Pokemon target, Battle battle) {
        return HitsMinimize.super.targetIsMinimized(target) ? 2 : 1;
    }


}
