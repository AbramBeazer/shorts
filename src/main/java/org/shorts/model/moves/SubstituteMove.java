package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class SubstituteMove extends Move {

    private int subHP;

    public SubstituteMove() {
        super("Substitute", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        subHP = (int) Math.ceil((double) user.getMaxHP() / 4);
        if (!user.hasVolatileStatus(SUBSTITUTE) && user.getCurrentHP() > subHP) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.setCurrentHP(user.getCurrentHP() - subHP);
        user.addVolatileStatus(new SubstituteStatus(subHP));
    }
}
