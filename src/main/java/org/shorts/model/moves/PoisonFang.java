package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.TOXIC_POISON;

public class PoisonFang extends Move implements BitingMove, GetsSheerForceBoost {

    public PoisonFang() {
        super("Poison Fang", 50, 100, Type.POISON, Category.PHYSICAL, Range.NORMAL, 24, true, 50);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (TOXIC_POISON.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.createToxic());
    }
}
