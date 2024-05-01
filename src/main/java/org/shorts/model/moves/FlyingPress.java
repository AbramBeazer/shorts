package org.shorts.model.moves;

import java.util.Set;

import org.shorts.model.types.Type;

public class FlyingPress extends PhysicalMove {

    private FlyingPress() {
        super("Flying Press", 100, 100, Type.FIGHTING, 95, true, 0);
    }

    @Override
    public double getTypeMultiplier(Type moveType, Set<Type> defenderTypes) {
        return super.getTypeMultiplier(Type.FIGHTING, defenderTypes) * super.getTypeMultiplier(
            Type.FLYING,
            defenderTypes);
    }

    public static final FlyingPress FLYING_PRESS = new FlyingPress();
}
