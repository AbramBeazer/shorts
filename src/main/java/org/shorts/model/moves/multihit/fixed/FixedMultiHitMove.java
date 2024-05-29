package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.types.Type;

public abstract class FixedMultiHitMove extends MultiHitMove {

    protected FixedMultiHitMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance,
        int hits) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance, hits, hits);
    }

}
