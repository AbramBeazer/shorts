package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class StruggleBug extends Move {

    public StruggleBug() {
        super("Struggle Bug", 50, 100, Type.BUG, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 32, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should I be checking to see if Mist is in effect?
        if (target.isDropPossible(StatEnum.SPATK) && !target.isBehindSub()) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, -1, StatEnum.SPATK);
    }
}
