package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.PARALYZE;
import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class ThunderFang extends Move implements BitingMove, GetsSheerForceBoost {

    private boolean calculatingParalyze;

    public ThunderFang() {
        super("Thunder Fang", 65, 95, Type.ELECTRIC, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 24, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        calculatingParalyze = true;
        if (PARALYZE.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }

        calculatingParalyze = false;
        if (FLINCH.isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (calculatingParalyze) {
            target.setStatus(Status.PARALYZE);
        } else {
            target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
        }
    }
}
