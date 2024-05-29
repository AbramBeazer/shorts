package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class CloseCombat extends Move {

    public CloseCombat() {
        super("Close Combat", 120, 100, Type.FIGHTING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 8, true, 100);
    }

    @Override
    public void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeDefense(-1);
        user.changeSpecialDefense(-1);
        user.afterDrop(target, battle);
    }

}
