package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class DizzyPunch extends Move implements PunchingMove {

    public DizzyPunch() {
        super("Dizzy Punch", 70, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 16, true, 20);
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
