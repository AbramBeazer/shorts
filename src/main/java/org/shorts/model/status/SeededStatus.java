package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.LiquidOoze.LIQUID_OOZE;
import static org.shorts.model.items.BigRoot.BIG_ROOT;

public class SeededStatus extends VolatileStatus {

    private int seederIndex;
    private boolean isAlly;

    public SeededStatus(int seederIndex, boolean isAlly) {
        super(VolatileStatusType.SEEDED, -1);
        this.seederIndex = seederIndex;
        this.isAlly = isAlly;
    }

    public int getSeederIndex() {
        return seederIndex;
    }

    public void drain(Pokemon afflicted, Battle battle) {
        final Pokemon drainer = isAlly
            ? battle.getCorrespondingTrainer(afflicted).getActivePokemon().get(seederIndex)
            : battle.getOpposingActivePokemon(afflicted).get(seederIndex);

        if (!drainer.hasFainted()) {
            final int damage = Math.max(afflicted.getMaxHP() / 16, 1);

            afflicted.takeDamage(damage);

            final int healed = drainer.getHeldItem() == BIG_ROOT ? (int) (damage * 1.3) : damage;

            if (afflicted.getAbility() == LIQUID_OOZE) {
                drainer.takeDamage(healed);
            } else if (!drainer.hasVolatileStatus(VolatileStatusType.HEAL_BLOCKED)) {
                drainer.heal(healed);
            }
        }
    }
}
