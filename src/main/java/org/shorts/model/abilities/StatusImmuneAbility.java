package org.shorts.model.abilities;

import java.util.List;

import org.shorts.model.status.AbstractStatusType;
import org.shorts.model.status.Status;
import org.shorts.model.status.volatilestatus.VolatileStatus;

public class StatusImmuneAbility extends Ability implements IgnorableAbility {

    private final List<AbstractStatusType> immunities;

    protected StatusImmuneAbility(String name, List<AbstractStatusType> immunities) {
        super(name);
        this.immunities = immunities;
    }

    private StatusImmuneAbility(String name, AbstractStatusType immunity) {
        this(name, List.of(immunity));
    }

    public List<AbstractStatusType> getImmunities() {
        return immunities;
    }
    //TODO: Figure out which methods need to be implemented.

    public static final StatusImmuneAbility LIMBER = new StatusImmuneAbility("Limber", Status.StatusType.PARALYZE);
    public static final StatusImmuneAbility IMMUNITY = new StatusImmuneAbility(
        "Immunity",
        List.of(Status.StatusType.POISON, Status.StatusType.TOXIC_POISON));
    public static final StatusImmuneAbility INSOMNIA = new StatusImmuneAbility(
        "Insomnia",
        List.of(Status.StatusType.SLEEP, VolatileStatus.VolatileStatusType.DROWSY));

    public static final StatusImmuneAbility VITAL_SPIRIT = new StatusImmuneAbility(
        "Vital Spirit",
        List.of(Status.StatusType.SLEEP, VolatileStatus.VolatileStatusType.DROWSY));

    //TODO: This should also affect allies in double battles.
    public static final StatusImmuneAbility SWEET_VEIL = new StatusImmuneAbility(
        "Sweet Veil",
        List.of(Status.StatusType.SLEEP, VolatileStatus.VolatileStatusType.DROWSY));

    public static final StatusImmuneAbility MAGMA_ARMOR = new StatusImmuneAbility(
        "Magma Armor",
        Status.StatusType.FREEZE);
    public static final StatusImmuneAbility WATER_VEIL = new StatusImmuneAbility(
        "Water Veil",
        Status.StatusType.BURN);

    public static final StatusImmuneAbility OBLIVIOUS = new StatusImmuneAbility(
        "Oblivious",
        VolatileStatus.VolatileStatusType.INFATUATED);

    public static final StatusImmuneAbility OWN_TEMPO = new StatusImmuneAbility(
        "Own Tempo",
        VolatileStatus.VolatileStatusType.CONFUSED);

    public static final StatusImmuneAbility INNER_FOCUS = new StatusImmuneAbility(
        "Inner Focus",
        VolatileStatus.VolatileStatusType.FLINCH);
}
