package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FuryCutter extends Move implements SlicingMove {

    private boolean lastAttemptHit = false;
    private int multipler = 1;
    private static final double BASE_POWER = 40;
    private static final double MAX_MULTIPLIER = 4;

    public FuryCutter() {
        super("Fury Cutter", BASE_POWER, 95, Type.BUG, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 0);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        return super.getPowerMultipliers(user, target, battle) * multipler;
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        final boolean hits = super.rollToHit(user, target, battle);
        if (!hits || !this.equals(user.getLastMoveUsed())) {
            multipler = 1;
        } else if (this.equals(user.getLastMoveUsed()) && lastAttemptHit && multipler < MAX_MULTIPLIER) {
            multipler *= 2;
        }
        lastAttemptHit = hits;
        return hits;
    }
}
