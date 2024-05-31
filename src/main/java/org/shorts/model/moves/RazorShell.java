package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class RazorShell extends Move implements SlicingMove {

    public RazorShell() {
        super("Razor Shell", 75, 95, Type.WATER, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 50);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.changeDefense(-1);
    }
}
