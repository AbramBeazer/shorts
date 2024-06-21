package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public class WhiteHerb extends HeldItem {

    private WhiteHerb() {
        super("White Herb", 10);
    }

    public static final WhiteHerb WHITE_HERB = new WhiteHerb();

    @Override
    public void afterDrop(Pokemon self, Pokemon opponent, Battle battle) {
        activate(self);
    }

    @Override
    public void onGainItem(Pokemon self, Battle battle) {
        activate(self);
    }

    private void activate(Pokemon self) {
        self.setStageAttack(Math.max(0, self.getStageAttack()));
        self.setStageDefense(Math.max(0, self.getStageDefense()));
        self.setStageSpecialAttack(Math.max(0, self.getStageSpecialAttack()));
        self.setStageSpecialDefense(Math.max(0, self.getStageSpecialDefense()));
        self.setStageSpeed(Math.max(0, self.getStageSpeed()));
        self.setStageAccuracy(Math.max(0, self.getStageAccuracy()));
        self.setStageEvasion(Math.max(0, self.getStageEvasion()));
    }
}
