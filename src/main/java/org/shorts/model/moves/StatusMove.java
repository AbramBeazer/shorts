package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class StatusMove extends Move {

    protected StatusMove(
        String name, double accuracy, Type type, int maxPP) {
        super(name, 0, accuracy, type, maxPP, false, 100);
    }

    protected StatusMove(
        String name, double accuracy, Type type, int maxPP, int priority) {
        super(name, 0, accuracy, type, maxPP, false, 100, priority);
    }
}
