package org.shorts.model.status;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Trainer;
import org.shorts.model.abilities.NullifyingAbility;
import org.shorts.model.abilities.StatusImmuneAbility;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.ICE;
import static org.shorts.model.types.Type.STEEL;

public class Status extends AbstractStatus {

    private final StatusType type;

    private Status(StatusType type, int turnsRemaining) {
        super(turnsRemaining);
        this.type = type;
    }

    public StatusType getType() {
        return type;
    }

    //to be used in most cases
    public static Status createSleep() {
        return new Status(StatusType.SLEEP, Main.RANDOM.nextInt(3) + 1);
    }

    //to be used for Rest and other cases where the sleep lasts a set number of turns.
    public static Status createSleepForTurns(int turnsRemaining) {
        return new Status(StatusType.SLEEP, turnsRemaining);
    }

    public static final Status NONE = new Status(StatusType.NONE, -1);
    public static final Status PARALYZE = new Status(StatusType.PARALYZE, -1);
    public static final Status BURN = new Status(StatusType.BURN, -1);
    public static final Status FREEZE = new Status(StatusType.FREEZE, -1);
    public static final Status POISON = new Status(StatusType.POISON, -1);
    public static final Status TOXIC_POISON = new Status(StatusType.TOXIC_POISON, -1);

    public boolean isStatusPossible(Pokemon user, Pokemon target, Battle battle) {
        Trainer trainer = battle.getPlayerOne().getLead() == target ? battle.getPlayerOne() : battle.getPlayerTwo();
        if (target.getStatus() != NONE || trainer.getSafeguardTurns() > 0
            || (target.isGroundedApplyNullifyingAbility(user.getAbility() instanceof NullifyingAbility)
            && battle.getTerrain() == Terrain.MISTY)
            || (target.getAbility() instanceof StatusImmuneAbility
            && ((StatusImmuneAbility) target.getAbility()).getImmunities().contains(this.getType()))) {
            return false;
        }
        switch (this.type) {
            case TOXIC_POISON:
            case POISON:
                return !(target.getTypes().contains(Type.POISON) || target.getTypes().contains(STEEL));
            case FREEZE:
                return !target.getTypes().contains(ICE);
            case SLEEP:
                return battle.getTerrain() != Terrain.ELECTRIC;
            case PARALYZE:
                return !target.getTypes().contains(ELECTRIC);
            case BURN:
                return !target.getTypes().contains(FIRE);
            default:
                return true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Status && this.type.equals(((Status) obj).type);
    }

    @Override
    public int hashCode() {
        return 151 * (type.ordinal() + 1) * Math.abs(turnsRemaining);
    }

    public enum StatusType implements AbstractStatusType {
        NONE,
        SLEEP,
        BURN,
        FREEZE,
        PARALYZE,
        POISON,
        TOXIC_POISON,
        FAINT
    }

}