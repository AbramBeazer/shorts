package org.shorts.model.moves.hex;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

public class BarbBarrage extends Move {

    static final int MULTIPLIER = 2;

    public BarbBarrage() {
        super("Barb Barrage", 60, 100, Type.POISON, Category.PHYSICAL, Range.NORMAL, 16, false, 50);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        if (target.getStatus().getType() == StatusType.POISON
            || target.getStatus().getType().equals(StatusType.TOXIC_POISON)) {

            return MULTIPLIER * super.getPower(user, target, battle);
        }
        return super.getPower(user, target, battle);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (StatusType.POISON.isStatusPossible(user, target, battle)
            && !target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(Status.POISON);
    }
}
