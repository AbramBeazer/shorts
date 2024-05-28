package org.shorts.model.moves.multihit;

import org.shorts.Main;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class MultiHitMove extends Move {

    private final int minHits;
    private final int maxHits;

    protected MultiHitMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance, int minHits, int maxHits) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
        this.minHits = minHits;
        this.maxHits = maxHits;
    }

    @Override
    public int getNumHits(boolean skillLink) {
        if (skillLink) {
            return maxHits;
        } else if (minHits == maxHits) {
            return maxHits;
        } else {
            return Main.RANDOM.nextInt((maxHits + 1) - minHits) + minHits;
        }
    }
}
