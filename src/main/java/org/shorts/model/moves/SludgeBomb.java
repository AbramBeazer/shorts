package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class SludgeBomb extends Move implements BallBombMove {

    public SludgeBomb() {
        super("Sludge Bomb", 90, 100, Type.POISON, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.POISON.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.POISON);
    }
}
