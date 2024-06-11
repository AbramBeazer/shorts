package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.Gem;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.NoItem.NO_ITEM;

public class Incinerate extends Move {

    public Incinerate() {
        super("Incinerate", 60, 100, Type.FIRE, Category.SPECIAL, Range.ALL_ADJACENT_OPPONENTS, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.getStatus().getType().equals(StatusType.FREEZE)) {
            System.out.println(target.getNickname() + " was thawed out!");
            target.setStatus(Status.NONE);
        }
        if ((target.getHeldItem() instanceof Berry || target.getHeldItem() instanceof Gem)
            && target.getAbility() != STICKY_HOLD) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setHeldItem(NO_ITEM);
    }
}
