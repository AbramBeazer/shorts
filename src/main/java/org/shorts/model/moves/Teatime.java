package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Teatime extends Move {

    public Teatime() {
        super("Teatime", 0, -1, Type.NORMAL, Category.STATUS, Range.ALL, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasFainted() && target.getHeldItem() instanceof Berry) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        ((Berry) target.getHeldItem()).eatOwnBerry(user);
    }
}
