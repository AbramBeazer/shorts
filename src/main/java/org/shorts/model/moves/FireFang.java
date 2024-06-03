package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.StatusType.BURN;
import static org.shorts.model.status.VolatileStatusType.FLINCH;

public class FireFang extends Move implements BitingMove {

    private boolean calculatingBurn;

    public FireFang() {
        super("Fire Fang", 65, 95, Type.FIRE, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 10);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (target.getStatus().getType().equals(StatusType.FREEZE)) {
            System.out.println(target.getNickname() + " was thawed out!");
            target.setStatus(Status.NONE);
        }
        calculatingBurn = true;
        if (BURN.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }

        calculatingBurn = false;
        if (FLINCH.isStatusPossible(target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (calculatingBurn) {
            target.setStatus(Status.BURN);
        } else {
            target.addVolatileStatus(new VolatileStatus(FLINCH, 1));
        }
    }
}
