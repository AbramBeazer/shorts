package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class BellyDrum extends Move {

    public BellyDrum() {
        super("Belly Drum", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 16, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getStageAttack() < 6 && user.getCurrentHP() > (user.getMaxHP() / 2)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.takeDamage(
            user.getMaxHP() / 2,
            String.format("%s sacrificed its HP to maximize Attack!", user.getDisplayName()));
        user.setStageAttack(6);
    }
}
