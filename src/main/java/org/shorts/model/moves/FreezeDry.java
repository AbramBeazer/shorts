package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FreezeDry extends SpecialMove {

    private FreezeDry() {
        super("Freeze-Dry", 70, 100, Type.ICE, 32, false, 10);
    }

    @Override
    public double getMultiplier(Pokemon attacker, Pokemon defender, Battle battle) throws Exception {
        Set<Type> modifiedDefenderTypes = defender.getTypes().stream().filter(type -> !type.equals(Type.WATER)).collect(
            Collectors.toSet());
        double multiplier = Type.getMultiplier(attacker.getTypes(), this.getType(), modifiedDefenderTypes);
        return defender.getTypes().contains(Type.WATER) ? multiplier * 2 : multiplier;
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!defender.getTypes().contains(Type.ICE)) {
            defender.setStatus(Status.FREEZE);
        }
    }

    public static final FreezeDry FREEZE_DRY = new FreezeDry();
}
