package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Crunch extends Move implements BitingMove, GetsSheerForceBoost {

    public Crunch() {
        super("Crunch", 80, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL, 24, true, 20);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should I be checking to see if Mist is in effect?
        if (target.isDropPossible(StatEnum.DEF) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.DEF);
    }
}
