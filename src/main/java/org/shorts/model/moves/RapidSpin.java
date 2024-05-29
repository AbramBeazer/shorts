package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class RapidSpin extends Move {

    public RapidSpin() {
        super("Rapid Spin", 50, 100, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 64, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeSpeed(1);
        Trainer trainer = battle.getPlayerOne().getLead() == user ? battle.getPlayerOne() : battle.getPlayerTwo();
        trainer.removeEntryHazards();
        user.removeVolatileStatus(VolatileStatusType.SEEDED);
        user.removeVolatileStatus(VolatileStatusType.BOUND);
    }
}
