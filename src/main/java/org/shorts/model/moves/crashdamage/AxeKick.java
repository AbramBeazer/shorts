package org.shorts.model.moves.crashdamage;

import org.shorts.battle.Battle;
import org.shorts.model.moves.GetsSheerForceBoost;
import org.shorts.model.moves.KickingMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class AxeKick extends CrashDamageMove implements GetsSheerForceBoost, KickingMove {

    public AxeKick() {
        super("Axe Kick", 120, 90, Type.FIGHTING, 16, 30);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (VolatileStatusType.CONFUSED.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(VolatileStatus.createConfusion());
    }
}
