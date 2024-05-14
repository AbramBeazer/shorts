package org.shorts.model.abilities;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AbstractStatusType;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;

public class StatusImmuneAbility extends Ability implements IgnorableAbility {

    private final Set<AbstractStatusType> immunities;

    protected StatusImmuneAbility(String name, Set<AbstractStatusType> immunities) {
        super(name);
        this.immunities = immunities;
    }

    private StatusImmuneAbility(String name, AbstractStatusType immunity) {
        this(name, Set.of(immunity));
    }

    public Set<AbstractStatusType> getImmunities() {
        return immunities;
    }

    //TODO: Figure out which methods need to be implemented.

    @Override
    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        if (this.getImmunities().contains(self.getStatus().getType())) {
            self.setStatus(Status.NONE);
        }
        //TODO: Investigate further how Own Tempo is supposed to work if it inherits confusion through Baton Pass. Bulbapedia said something odd about how from Gen 5–7, "it will not be cured of confusion until after a Pokémon takes its turn (uses a move, switches out, etc.)."
        for (AbstractStatusType type : immunities) {
            if (type instanceof VolatileStatusType) {
                self.removeVolatileStatus((VolatileStatusType) type);
            }
        }
    }

    @Override
    public void onGainAbility(Pokemon self) {
        if (this.getImmunities().contains(self.getStatus().getType())) {
            self.setStatus(Status.NONE);
        }
        //TODO: Investigate further how Own Tempo is supposed to work if it inherits confusion through Baton Pass. Bulbapedia said something odd about how from Gen 5–7, "it will not be cured of confusion until after a Pokémon takes its turn (uses a move, switches out, etc.)."
        for (AbstractStatusType type : immunities) {
            if (type instanceof VolatileStatusType) {
                self.removeVolatileStatus((VolatileStatusType) type);
            }
        }
    }

    public static final StatusImmuneAbility LIMBER = new StatusImmuneAbility("Limber", StatusType.PARALYZE);
    public static final StatusImmuneAbility IMMUNITY = new StatusImmuneAbility(
        "Immunity",
        Set.of(StatusType.POISON, StatusType.TOXIC_POISON));
    public static final StatusImmuneAbility INSOMNIA = new StatusImmuneAbility(
        "Insomnia",
        Set.of(StatusType.SLEEP, VolatileStatusType.DROWSY));

    public static final StatusImmuneAbility VITAL_SPIRIT = new StatusImmuneAbility(
        "Vital Spirit",
        Set.of(StatusType.SLEEP, VolatileStatusType.DROWSY));

    //TODO: This should also affect allies in double battles.
    public static final StatusImmuneAbility SWEET_VEIL = new StatusImmuneAbility(
        "Sweet Veil",
        Set.of(StatusType.SLEEP, VolatileStatusType.DROWSY));

    public static final StatusImmuneAbility MAGMA_ARMOR = new StatusImmuneAbility(
        "Magma Armor",
        StatusType.FREEZE);
    public static final StatusImmuneAbility WATER_VEIL = new StatusImmuneAbility(
        "Water Veil",
        StatusType.BURN);

    public static final StatusImmuneAbility OBLIVIOUS = new StatusImmuneAbility(
        "Oblivious",
        VolatileStatusType.INFATUATED);

    public static final StatusImmuneAbility OWN_TEMPO = new StatusImmuneAbility(
        "Own Tempo",
        VolatileStatusType.CONFUSED);

    public static final StatusImmuneAbility INNER_FOCUS = new StatusImmuneAbility(
        "Inner Focus",
        VolatileStatusType.FLINCH);
}
