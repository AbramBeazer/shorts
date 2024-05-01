package org.shorts.model.moves;

import java.util.Set;

import org.shorts.model.types.Type;

import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FLYING;

public class FlyingPress extends PhysicalMove {

    private FlyingPress(Type moveType) {
        super("Flying Press", 100, 100, moveType, 95, true, 0);
    }

    @Override
    public double getTypeMultiplier(Set<Type> defenderTypes) {
        return super.getTypeMultiplier(defenderTypes) * Type.getTypeMultiplier(FLYING, defenderTypes);
    }

    public static final FlyingPress FLYING_PRESS = new FlyingPress(FIGHTING);
}
