package org.shorts.model.moves.trapping.binding;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public abstract class BindingMove extends Move {

    protected BindingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
    }

}
