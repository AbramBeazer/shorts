package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class SpecialMove extends Move {

    protected SpecialMove(
        String name, double power, double accuracy, Type type, int maxPP, boolean contact, int secondaryEffectChance) {
        super(name, power, accuracy, type, maxPP, contact, secondaryEffectChance);
    }

    protected SpecialMove(
        String name,
        double power,
        double accuracy,
        Type type,
        int maxPP,
        boolean contact,
        int secondaryEffectChance,
        int priority) {
        super(name, power, accuracy, type, maxPP, contact, secondaryEffectChance, priority);
    }
}
