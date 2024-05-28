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
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        attacker.changeEvasion(1);
        attacker.addVolatileStatus(new VolatileStatus(VolatileStatusType.MINIMIZED, -1));
    }
}
