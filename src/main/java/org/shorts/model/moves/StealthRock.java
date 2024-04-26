package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class StealthRock extends StatusMove {

    public StealthRock() {
        super("Stealth Rock", 0, Type.ROCK, 32);
    }

    public static final StealthRock STEALTH_ROCK = new StealthRock();
}
