package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FuryCutter extends Move implements SlicingMove {

    private int consecutiveHits = 0;
    //TODO: Add some way to set consecutive hits to 0 if another move is selected or if this Pokemon switches out.

    public FuryCutter() {
        super("Fury Cutter", 40, 95, Type.BUG, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 0);
    }

    @Override
    public double getPower() {
        final double power = super.getPower() * Math.pow(2, consecutiveHits);
        consecutiveHits++;
        return power;
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        final boolean hits = super.rollToHit(user, target, battle);
        if (!hits) {
            consecutiveHits = 0;
        }
        return hits;
    }

    public int getConsecutiveHits() {
        return consecutiveHits;
    }

    public void setConsecutiveHits(int consecutiveHits) {
        this.consecutiveHits = consecutiveHits;
    }

}
