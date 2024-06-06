package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class DarkPulse extends Move implements PulseEffect {

    public DarkPulse() {
        super("Dark Pulse", 80, 100, Type.DARK, Category.SPECIAL, Range.SINGLE_ANY, 24, false, 20);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (FLINCH.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
    }
}
