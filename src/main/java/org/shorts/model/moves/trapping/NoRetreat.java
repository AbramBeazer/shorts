package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;
import static org.shorts.model.status.VolatileStatusType.NO_RETREAT;

public class NoRetreat extends Move {

    public NoRetreat() {
        super("No Retreat", 0, -1, Type.FIGHTING, Category.STATUS, Range.SELF, 8, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (NO_RETREAT.isStatusPossible(user, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeAttack(1);
        user.changeDefense(1);
        user.changeSpecialAttack(1);
        user.changeSpecialDefense(1);
        user.changeSpeed(1);
        if (!user.hasVolatileStatus(CANT_ESCAPE)) {
            user.addVolatileStatus(new VolatileStatus(NO_RETREAT, -1));
        }
    }
}
