package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class FreezeDry extends Move {

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
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (Status.FREEZE.isStatusPossible(defender, battle)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.FREEZE);
    }

}
