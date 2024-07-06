package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class FreezeDry extends Move implements GetsSheerForceBoost {

    public FreezeDry() {
        super("Freeze-Dry", 70, 100, Type.ICE, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 32, false, 10);
    }

    @Override
    public double getBaseTypeMultiplier(Set<Type> defenderTypes) {
        Set<Type> modifiedDefenderTypes = defenderTypes.stream()
            .filter(type -> !type.equals(Type.WATER))
            .collect(Collectors.toSet());
        double multiplier = super.getBaseTypeMultiplier(modifiedDefenderTypes);
        return defenderTypes.contains(Type.WATER) ? multiplier * 2 : multiplier;
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.FREEZE.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.FREEZE);
    }

}
