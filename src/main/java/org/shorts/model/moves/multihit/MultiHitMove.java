package org.shorts.model.moves.multihit;

import org.shorts.Main;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public abstract class MultiHitMove extends Move {

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
        int secondaryEffectChance,
        int minHits,
        int maxHits) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
        this.minHits = minHits;
        this.maxHits = maxHits;
    }

    @Override
    public int getNumHits(boolean skillLink, boolean loadedDice) {
        if (minHits == maxHits) {
            return maxHits;
        } else if (skillLink) {
            return maxHits;
        } else if (loadedDice) {
            return Main.RANDOM.nextInt(2) + 4;
        } else {
            final int randNum = Main.RANDOM.nextInt(20);
            if (randNum < 7) {
                return 2;
            } else if (randNum < 14) {
                return 3;
            } else if (randNum < 17) {
                return 4;
            } else {
                return 5;
            }
        }
    }
}
