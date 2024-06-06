package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Pickup;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.NoItem.NO_ITEM;

public class Pluck extends Move {

    public Pluck() {
        super("Pluck", 60, 100, Type.FLYING, Category.PHYSICAL, Range.SINGLE_ANY, 32, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.getHeldItem() instanceof Berry && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)
            && target.getAbility() != STICKY_HOLD) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        HeldItem originalItem = user.getHeldItem();
        HeldItem originalConsumedItem = user.getConsumedItem();

        Berry berry = (Berry) target.getHeldItem();
        target.setHeldItem(NO_ITEM);
        System.out.println(user.getNickname() + " stole " + target.getNickname() + "'s " + berry.getName() + "!");

        berry.tryEatingBerry(user, battle);

        //This is necessary because eating a berry sets the user's item to NO_ITEM.
        user.setHeldItem(originalItem);
        user.setConsumedItem(originalConsumedItem);
        Pickup.removeFromConsumedItems(user);
    }
}
