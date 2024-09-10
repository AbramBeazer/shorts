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
        super(name + " Berry", 10);
        this.threshold = threshold;
    }

    private double computeThreshold(Pokemon user) {
        if (this.threshold == 0.25 && user.getAbility() == GLUTTONY && !user.hasVolatileStatus(ABILITY_SUPPRESSED)) {
            return 0.5;
        }
        return this.threshold;
    }

    protected boolean isWithinThreshold(Pokemon user) {
        return !user.hasFainted() && user.getCurrentHP() < user.getMaxHP() * computeThreshold(user);
    }

    public boolean isUnnerveActive(Pokemon user, Battle battle) {
        for (Pokemon opponent : battle.getOpposingActivePokemon(user)) {
            if (opponent.getAbility() instanceof OpponentCantEatBerriesAbility && !opponent.hasVolatileStatus(
                ABILITY_SUPPRESSED)) {
                return true;
            }
        }
        return false;
    }

    public boolean canDoEffect(Pokemon user) {
        return true;
    }

    public boolean tryEatingOwnBerry(Pokemon user, Battle battle) {
        if (isWithinThreshold(user) && !isUnnerveActive(user, battle) && canDoEffect(user)) {
            eatOwnBerry(user);
            return true;
        } else {
            return false;
        }
    }

    public void eatOwnBerry(Pokemon user) {
        this.doEffect(user);
        printMessageAndAfterEffects(user);

        user.setHeldItem(NO_ITEM);
        user.setConsumedItem(this);
        Pickup.addToConsumedItems(user);
    }

    public void tryEatingOtherBerry(Pokemon user) {
        if (canDoEffect(user)) {
            doEffect(user);
            printMessageAndAfterEffects(user);
        }
        //TODO: What if a CudChew user is at full health and steals a berry with Pluck?
        // The berry can't heal it, but will it activate Cud Chew and heal the user if it takes damage before the end of the next turn?
    }

    public abstract void doEffect(Pokemon user);

    public void printMessageAndAfterEffects(Pokemon user) {
        System.out.println(user.getNickname() + " ate the " + this.getName());

        if (!user.hasVolatileStatus(ABILITY_SUPPRESSED)) {
            if (user.getAbility() == CHEEK_POUCH && !user.hasVolatileStatus(HEAL_BLOCKED)) {
                user.heal(user.getMaxHP() / 3);
            } else if (user.getAbility() instanceof CudChew) {
                ((CudChew) user.getAbility()).setBerryToRegurgitate(this);
            }
        }
    }
}
