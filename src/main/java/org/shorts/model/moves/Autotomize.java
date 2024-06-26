package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AutotomizedStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.AUTOTOMIZED;

public class Autotomize extends Move {

    public Autotomize() {
        super("Autotomize", 0, -1, Type.STEEL, Category.STATUS, Range.SELF, 24, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.canChangeStat(2, StatEnum.SPEED)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeSpeed(2);
        if (user.hasVolatileStatus(AUTOTOMIZED)) {
            ((AutotomizedStatus) user.getVolatileStatus(AUTOTOMIZED)).incrementLevels();
        } else {
            user.addVolatileStatus(new AutotomizedStatus());
        }
    }

    // NOT IN GEN 9
}
