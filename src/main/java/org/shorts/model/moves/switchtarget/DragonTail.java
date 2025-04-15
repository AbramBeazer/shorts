package org.shorts.model.moves.switchtarget;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class DragonTail extends SwitchTargetMove {

    public DragonTail() {
        super("Dragon Tail", 60, 90, Type.DRAGON, Category.PHYSICAL, Range.NORMAL, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!target.hasVolatileStatus(SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }
}
