package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class DireClaw extends Move implements GetsSheerForceBoost {

    private Status currentStatus;

    public DireClaw() {
        super("Dire Claw", 80, 100, Type.POISON, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 24, true, 50);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final int rand = Main.RANDOM.nextInt(6);
        if (rand == 0) {
            currentStatus = Status.POISON;
        } else if (rand == 1) {
            currentStatus = Status.PARALYZE;
        } else if (rand == 2) {
            currentStatus = Status.createSleep();
        }

        if (currentStatus != null && currentStatus.getType().isStatusPossible(user, target, battle)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setStatus(currentStatus);
        target.afterStatus(user, battle);
        currentStatus = null;
    }
}
