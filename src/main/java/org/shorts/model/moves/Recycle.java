package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.items.NoItem.NO_ITEM;

public class Recycle extends Move {

    public Recycle() {
        super("Recycle", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getHeldItem() == NO_ITEM && user.getConsumedItem() != NO_ITEM) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.setHeldItem(user.getConsumedItem());
        user.setConsumedItem(NO_ITEM);
    }
}
