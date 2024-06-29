package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class MagicCoat extends Move {

    public MagicCoat() {
        super("Magic Coat", 0, -1, Type.PSYCHIC, Category.STATUS, Range.SELF, 24, false, 100);
    }

    @Override
    public int getPriority(Pokemon attacker, Battle battle) {
        return 4;
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.MAGIC_COAT, 1));
    }
}
