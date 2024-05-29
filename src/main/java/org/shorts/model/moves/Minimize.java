package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class Minimize extends Move {

    public Minimize() {
        super("Minimize", 0, -1, Type.NORMAL, Category.STATUS, Range.SELF, 16, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.changeEvasion(1);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, -1));
    }
}
