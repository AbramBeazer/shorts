package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SeededStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class LeechSeed extends Move {

    public LeechSeed() {
        super("Leech Seed", 0, 90, Type.GRASS, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (VolatileStatusType.SEEDED.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final int seederIndex;
        final boolean isAlly;
        if (battle.getActiveMonsPerSide() == 1) {
            seederIndex = 0;
            isAlly = false;
        } else {
            seederIndex = battle.getCorrespondingTrainer(user).getActivePokemon().indexOf(user);
            isAlly = battle.getCorrespondingTrainer(user) == battle.getCorrespondingTrainer(target);
        }
        target.addVolatileStatus(new SeededStatus(seederIndex, isAlly));
    }
}
