package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class SyrupBomb extends Move implements BallBombMove, GetsSheerForceBoost {

    public SyrupBomb() {
        super("Syrup Bomb", 60, 85, Type.GRASS, Category.SPECIAL, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Does this hit through Substitute?
        if (!user.hasVolatileStatus(VolatileStatusType.SYRUP_BOMBED)
            && !user.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.SYRUP_BOMBED, 3));
        //TODO: The speed drop applies at the end of the turn, right?
    }
}
