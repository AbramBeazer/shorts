package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class RollingKick extends Move implements KickingMove {

    public RollingKick() {
        super("Rolling Kick", 60, 85, Type.FIGHTING, Category.PHYSICAL, Range.NORMAL, 24, true, 0);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (VolatileStatusType.FLINCH.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.FLINCH, 1));
    }
}
