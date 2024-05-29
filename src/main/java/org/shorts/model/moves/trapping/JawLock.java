package org.shorts.model.moves.trapping;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;

public class JawLock extends TrappingMove {

    public JawLock() {
        super("Jaw Lock", 80, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (CANT_ESCAPE.isStatusPossible(user, battle) && CANT_ESCAPE.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        super.applySecondaryEffect(user, target, battle);
        user.addVolatileStatus(VolatileStatus.CANT_ESCAPE_INDEFINITE);
    }
}
