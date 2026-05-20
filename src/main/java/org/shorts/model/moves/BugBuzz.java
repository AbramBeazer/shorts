package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BugBuzz extends Move implements SoundEffect {

    public BugBuzz() {
        super("Bug Buzz", 90, 100, Type.BUG, Category.SPECIAL, Range.NORMAL, 16, false, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPDEF, true)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, -1, StatEnum.SPDEF);
    }
}
