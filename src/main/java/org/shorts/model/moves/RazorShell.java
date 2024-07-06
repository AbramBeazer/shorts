package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class RazorShell extends Move implements SlicingMove, GetsSheerForceBoost {

    public RazorShell() {
        super("Razor Shell", 75, 95, Type.WATER, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 50);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.isDropPossible(StatEnum.DEF)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeStat(-1, StatEnum.DEF);
    }
}
