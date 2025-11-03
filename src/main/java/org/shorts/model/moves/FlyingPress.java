package org.shorts.model.moves;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.MINIMIZED;
import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FLYING;

public class FlyingPress extends Move implements HitsMinimize {

    public FlyingPress() {
        super("Flying Press", 100, 100, FIGHTING, Category.PHYSICAL, Range.SINGLE_ANY, 95, true, 0);
    }

    @Override
    public double getBaseTypeMultiplier(Set<Type> defenderTypes) {
        return super.getBaseTypeMultiplier(defenderTypes) * Type.getTypeMultiplier(FLYING, defenderTypes);
    }
}
