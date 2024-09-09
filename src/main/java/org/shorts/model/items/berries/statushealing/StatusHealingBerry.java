package org.shorts.model.items.berries.statushealing;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AbstractStatusType;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;

public class StatusHealingBerry extends Berry {

    private List<AbstractStatusType> statuses;

    private StatusHealingBerry(String name, List<AbstractStatusType> status) {
        super(name);
        this.statuses = status;
    }

    @Override
    public void onGainItem(Pokemon self, Battle battle) {
        tryEatingOwnBerry(self, battle);
    }

    @Override
    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
        tryEatingOwnBerry(self, battle);
    }

    @Override
    public boolean canDoEffect(Pokemon user) {
        return this.statuses.contains(user.getStatus().getType())
            || this.statuses.stream().filter(VolatileStatusType.class::isInstance).map(VolatileStatusType.class::cast)
            .anyMatch(user::hasVolatileStatus);
    }

    @Override
    public void doEffect(Pokemon user) {
        if (this.statuses.contains(user.getStatus().getType())) {
            user.setStatus(Status.NONE);
        }

        this.statuses.forEach(ast -> {
            if (ast instanceof VolatileStatusType) {
                user.removeVolatileStatus((VolatileStatusType) ast);
            }
        });
    }

    public static final StatusHealingBerry RAWST_BERRY = new StatusHealingBerry("Rawst", List.of(StatusType.BURN));
    public static final StatusHealingBerry ASPEAR_BERRY = new StatusHealingBerry("Aspear", List.of(StatusType.FREEZE));
    public static final StatusHealingBerry CHERI_BERRY = new StatusHealingBerry("Cheri", List.of(StatusType.PARALYZE));
    public static final StatusHealingBerry CHESTO_BERRY = new StatusHealingBerry("Chesto", List.of(StatusType.SLEEP));
    public static final StatusHealingBerry PECHA_BERRY = new StatusHealingBerry(
        "Pecha",
        List.of(StatusType.POISON, StatusType.TOXIC_POISON));
    public static final StatusHealingBerry LUM_BERRY = new StatusHealingBerry(
        "Lum",
        List.of(
            StatusType.BURN,
            StatusType.FREEZE,
            StatusType.PARALYZE,
            StatusType.SLEEP,
            StatusType.POISON,
            StatusType.TOXIC_POISON,
            VolatileStatusType.CONFUSED));
}
