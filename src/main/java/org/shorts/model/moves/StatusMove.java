package org.shorts.model.moves;

import org.shorts.model.types.Type;

public abstract class StatusMove extends Move {

    protected StatusMove(
        String name, double accuracy, Type type, int maxPP) {
        super(name, 0, accuracy, type, maxPP, false, 100);
    }
}
