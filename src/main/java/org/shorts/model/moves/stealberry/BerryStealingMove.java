package org.shorts.model.moves.stealberry;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Pickup;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.NoItem.NO_ITEM;

public abstract class BerryStealingMove extends Move {

    protected BerryStealingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
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
        int originalIndex = Pickup.getConsumedItemsIndexForUser(user);

        Berry berry = (Berry) target.getHeldItem();
        target.setHeldItem(NO_ITEM);
        System.out.println(user.getNickname() + " stole " + target.getNickname() + "'s " + berry.getName() + "!");

        if (berry.tryEatingBerry(user, battle)) {
            //This is necessary because eating a berry sets the user's item to NO_ITEM.
            user.setHeldItem(originalItem);
            user.setConsumedItem(originalConsumedItem);
            Pickup.removeFromConsumedItems(user);
            Pickup.insertPokemonAtIndex(originalIndex, user);
        }
    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {
        return super.calculateDamage(user, target, battle);
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        super.executeOnTarget(user, target, battle);
    }
}
