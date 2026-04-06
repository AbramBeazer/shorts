package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Pickup;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.items.NoItem.*;

public class WhiteHerb extends HeldItem {

    private WhiteHerb() {
        super("White Herb", 10);
    }

    public static final WhiteHerb WHITE_HERB = new WhiteHerb();

    //TODO: Is this when White Herb should activate, or should this activate at end of turn?
    @Override
    public void afterDrop(Pokemon self, Pokemon cause, Battle battle) {
        activate(self);
    }

    @Override
    public void onGainItem(Pokemon self, Battle battle) {
        activate(self);
    }

    private void activate(Pokemon self) {
        if (self.getStageAttack() < 0 || self.getStageDefense() < 0 || self.getStageSpecialAttack() < 0
            || self.getStageSpecialDefense() < 0 || self.getStageSpeed() < 0) {

            self.setStageAttack(Math.max(0, self.getStageAttack()));
            self.setStageDefense(Math.max(0, self.getStageDefense()));
            self.setStageSpecialAttack(Math.max(0, self.getStageSpecialAttack()));
            self.setStageSpecialDefense(Math.max(0, self.getStageSpecialDefense()));
            self.setStageSpeed(Math.max(0, self.getStageSpeed()));
            self.setStageAccuracy(Math.max(0, self.getStageAccuracy()));
            self.setStageEvasion(Math.max(0, self.getStageEvasion()));
            self.setConsumedItem(this);
            self.setHeldItem(NO_ITEM);
            Pickup.addToConsumedItems(self);
        }
    }
}
