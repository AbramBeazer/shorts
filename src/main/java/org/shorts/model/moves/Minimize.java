package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
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
        //TODO: Verify that the minimized status should still be added if the move is used when evasion is already maxed out.
        user.changeStat(2, StatEnum.EVASION);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, -1));
    }
}
