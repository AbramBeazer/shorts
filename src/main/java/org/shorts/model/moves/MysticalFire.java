package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class MysticalFire extends Move {

    public MysticalFire() {
        super("Mystical Fire", 75, 100, Type.FIRE, Category.SPECIAL, Range.NORMAL, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.SPATK) && !target.isBehindSub()) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(battle, user, -1, StatEnum.SPATK);
    }
}
