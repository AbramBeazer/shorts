package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class RockWrecker extends Move implements BallBombMove {

    public RockWrecker() {
        super("Rock Wrecker", 150, 90, Type.ROCK, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 8, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        applySecondaryEffect(user, target, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.MUST_RECHARGE, 1));
    }
}
