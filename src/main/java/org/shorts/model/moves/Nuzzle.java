package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.*;

public class Nuzzle extends Move {

    public Nuzzle(){
        super("Nuzzle", 20, 100, Type.ELECTRIC, Category.PHYSICAL, Range.NORMAL_SINGLE_ADJACENT_ANY, 32, true, 100);
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
