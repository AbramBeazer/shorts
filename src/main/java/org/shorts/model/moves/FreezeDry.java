package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;

public class FreezeDry extends Move {

    public FreezeDry() {
        super("Freeze-Dry", 70, 100, Type.ICE, MoveGroup.SPECIAL, 32, false);
    }

    @Override
    public double getMultiplier(Set<Type> attackerTypes, Set<Type> defenderTypes) throws Exception {
        Set<Type> modifiedDefenderTypes = defenderTypes.stream().filter(type -> !type.equals(Type.WATER)).collect(
            Collectors.toSet());
        double multiplier = Type.getMultiplier(attackerTypes, this.getType(), modifiedDefenderTypes);
        return defenderTypes.contains(Type.WATER) ? multiplier * 2 : multiplier;
    }

    @Override
    public void secondaryEffect(Pokemon attacker, Pokemon defender) {
        if (RANDOM.nextInt(10) == 0) {
            defender.setStatus(Status.FREEZE);
        }
    }
}
