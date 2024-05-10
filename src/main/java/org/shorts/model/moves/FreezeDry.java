package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class FreezeDry extends SpecialMove {

    private FreezeDry() {
        super("Freeze-Dry", 70, 100, Type.ICE, 32, false, 10);
    }

    @Override
    public double getTypeMultiplier(Set<Type> defenderTypes) {
        Set<Type> modifiedDefenderTypes = defenderTypes.stream().filter(type -> !type.equals(Type.WATER)).collect(
            Collectors.toSet());
        double multiplier = super.getTypeMultiplier(modifiedDefenderTypes);
        return defenderTypes.contains(Type.WATER) ? multiplier * 2 : multiplier;
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (Status.FREEZE.isStatusPossible(attacker, defender, battle)) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.FREEZE);
    }

    public static final FreezeDry FREEZE_DRY = new FreezeDry();
}
