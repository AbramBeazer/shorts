package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class DireClaw extends Move implements GetsSheerForceBoost {

    public DireClaw() {
        super("Dire Claw", 80, 100, Type.POISON, Category.PHYSICAL, Range.NORMAL, 24, true, 50);
    }

    //TODO: Is any of this right? If it rolls poison on a target that's already poisoned, does it then just paralyze it instead?

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final Status currentStatus;

        final int rand = Main.RANDOM.nextInt(3);
        if (rand == 0) {
            currentStatus = Status.POISON;
        } else if (rand == 1) {
            currentStatus = Status.PARALYZE;
        } else {
            currentStatus = Status.createSleep();
        }
        if (currentStatus.getType().isStatusPossible(user, target, battle)) {
            target.setStatus(currentStatus);
            target.afterStatus(user, battle);
        }
    }
}
