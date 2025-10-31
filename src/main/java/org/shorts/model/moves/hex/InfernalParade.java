package org.shorts.model.moves.hex;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class InfernalParade extends Move {

    static final int MULTIPLIER = 2;

    public InfernalParade() {
        super("Infernal Parade", 60, 100, Type.GHOST, Category.SPECIAL, Range.NORMAL, 24, false, 30);
    }

    @Override
    public double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double base = super.getPowerMultipliers(user, target, battle);
        return (target.getStatus() != null && target.getStatus().getType() != StatusType.NONE)
            ? base * MULTIPLIER
            : base;
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.BURN.isStatusPossible(user, target, battle) && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.BURN);
    }
}
