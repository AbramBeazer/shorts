package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.OpponentCantEatBerriesAbility;
import org.shorts.model.items.HeldItem;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.CheekPouch.CHEEK_POUCH;
import static org.shorts.model.abilities.Gluttony.GLUTTONY;
import static org.shorts.model.items.NoItem.NO_ITEM;

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

    @Override
    public void afterTurn(Pokemon user, Pokemon opponent, Battle battle) {
        if (user.getCurrentHP() < user.getMaxHP() * computeThreshold(user)) {
            onEat(user, battle);
        }
    }

    public void onEat(Pokemon user, Battle battle) {
        if (!(battle.getOpposingTrainer(user).getLead().getAbility() instanceof OpponentCantEatBerriesAbility)) {
            System.out.println(user.getNickname() + " ate its " + this.getName());
            user.setHeldItem(NO_ITEM);
            user.setConsumedItem(this);
            if (user.getAbility() == CHEEK_POUCH) {
                user.heal(user.getMaxHP() / 3);
            }
        }
    }
}
