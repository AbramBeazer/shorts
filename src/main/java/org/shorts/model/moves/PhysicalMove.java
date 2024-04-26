package org.shorts.model.moves;

import org.shorts.model.types.Type;

public abstract class PhysicalMove extends Move {

    protected PhysicalMove(
        String name, double power, double accuracy, Type type, int maxPP, boolean contact, int secondaryEffectChance) {
        super(name, power, accuracy, type, maxPP, contact, secondaryEffectChance);
    }
}
