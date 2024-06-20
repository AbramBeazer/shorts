package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.CudChew;
import org.shorts.model.abilities.OpponentCantEatBerriesAbility;
import org.shorts.model.abilities.Pickup;
import org.shorts.model.items.HeldItem;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.CheekPouch.CHEEK_POUCH;
import static org.shorts.model.abilities.Gluttony.GLUTTONY;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.HEAL_BLOCKED;

public abstract class Berry extends HeldItem {

    private final double threshold;

    protected Berry(String name) {
        this(name, -1);
    }

    protected Berry(String name, double threshold) {
        super(name + " Berry");
        this.threshold = threshold;
    }

    private double computeThreshold(Pokemon user) {
        if (this.threshold == 0.25 && user.getAbility() == GLUTTONY) {
            return 0.5;
        }
        return this.threshold;
    }

    //TODO: Is this correct? Do berries activate at the end of the turn from Gen 4 onward?
    @Override
    public void afterTurn(Pokemon user, Battle battle) {
        if (!user.hasFainted() && user.getCurrentHP() < user.getMaxHP() * computeThreshold(user)) {
            tryEatingBerry(user, battle);
        }
    }

    public boolean tryEatingBerry(Pokemon user, Battle battle) {
        for (Pokemon opponent : battle.getOpposingActivePokemon(user)) {
            if (opponent.getAbility() instanceof OpponentCantEatBerriesAbility && !opponent.hasVolatileStatus(
                ABILITY_SUPPRESSED)) {
                return false;
            }
        }
        this.eatBerry(user);
        return true;
    }

    public void eatBerry(Pokemon user) {

        this.doEffect(user);
        System.out.println(user.getNickname() + " ate its " + this.getName());
        user.setHeldItem(NO_ITEM);
        user.setConsumedItem(this);
        Pickup.addToConsumedItems(user);

        if (!user.hasVolatileStatus(ABILITY_SUPPRESSED)) {
            if (user.getAbility() == CHEEK_POUCH && !user.hasVolatileStatus(HEAL_BLOCKED)) {
                user.heal(user.getMaxHP() / 3);
            } else if (user.getAbility() instanceof CudChew) {
                ((CudChew) user.getAbility()).setBerryToRegurgitate(this);
            }
        }
    }

    public void doEffect(Pokemon user) {
    }
}
