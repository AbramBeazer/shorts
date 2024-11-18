package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.PARALYZE;

public class ThunderWave extends Move implements AffectedByMagicBounce {

    public ThunderWave() {
        super("Thunder Wave", 0, 90, Type.ELECTRIC, Category.STATUS, Range.SINGLE_ADJACENT_ANY, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (PARALYZE.isStatusPossible(user, target, battle) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.PARALYZE);
    }
}
