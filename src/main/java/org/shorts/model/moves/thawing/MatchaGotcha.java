package org.shorts.model.moves.thawing;

import org.shorts.battle.Battle;
import org.shorts.model.moves.HealingMove;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

public class MatchaGotcha extends ThawingMove implements HealingMove {

    public MatchaGotcha() {
        super("Matcha Gotcha", 80, 90, Type.GRASS, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 24, false, 20);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }

    //TODO: Implement HP draining.
}
