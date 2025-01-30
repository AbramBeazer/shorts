package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class Toxic extends Move {

    public Toxic() {
        super("Toxic", 0, 90, Type.POISON, Category.STATUS, Range.NORMAL_SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        if (user.getTypes().contains(Type.POISON)) {
            return true;
        }
        return super.rollToHit(user, target, battle);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.TOXIC_POISON.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.createToxic());
    }
}
