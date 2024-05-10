package org.shorts.model.moves;

import java.util.Set;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.battle.Terrain.MISTY;
import static org.shorts.model.status.Status.StatusType.FREEZE;

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
        Trainer trainer = battle.getPlayerOne().getLead() == defender ? battle.getPlayerOne() : battle.getPlayerTwo();
        if (defender.getStatus() != Status.NONE && !defender.getTypes().contains(Type.ICE)
            && !(defender.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) defender.getAbility()).getImmunities().contains(FREEZE))
            && !(defender.isGrounded() && battle.getTerrain() == MISTY)
            && trainer.getSafeguardTurns() > 0) {
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    public void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setStatus(Status.FREEZE);
    }

    public static final FreezeDry FREEZE_DRY = new FreezeDry();
}
