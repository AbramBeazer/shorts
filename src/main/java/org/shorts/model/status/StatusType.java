package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Trainer;
import org.shorts.battle.Weather;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Corrosion.*;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.ICE;
import static org.shorts.model.types.Type.STEEL;

public enum StatusType implements AbstractStatusType {
    NONE,
    SLEEP,
    BURN,
    FREEZE,
    PARALYZE,
    POISON,
    TOXIC_POISON,
    FAINTED;

    @Override
    public boolean isStatusPossible(Pokemon user, Pokemon target, Battle battle) {
        final Trainer trainer =
            battle.getCorrespondingTrainer(target);
        if (target.getStatus().getType() != NONE || trainer.getSafeguardTurns() > 0 || (target.isGrounded()
            && battle.getTerrain() == Terrain.MISTY) || (!target.hasVolatileStatus(ABILITY_IGNORED)
            && target.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) target.getAbility()).getImmunities().contains(this))) {
            return false;
        }
        switch (this) {
            case TOXIC_POISON:
            case POISON:
                return user.getAbility() == CORROSION
                    || !(target.getTypes().contains(Type.POISON) || target.getTypes().contains(STEEL));
            case FREEZE:
                return !target.getTypes().contains(ICE) && battle.getWeather() != Weather.SUN
                    && battle.getWeather() != Weather.EXTREME_SUN;
            case SLEEP:
                return !(target.isGrounded() && battle.getTerrain() == Terrain.ELECTRIC);
            case PARALYZE:
                return !target.getTypes().contains(ELECTRIC);
            case BURN:
                return !target.getTypes().contains(FIRE);
            default:
                return true;
        }
    }
}