package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class ThroatChop extends Move {

    public ThroatChop() {
        super("Throat Chop", 80, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL, 24, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasVolatileStatus(VolatileStatusType.THROAT_CHOPPED)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(VolatileStatusType.THROAT_CHOPPED, 2);
    }
}
