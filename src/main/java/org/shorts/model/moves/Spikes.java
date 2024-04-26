package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Spikes extends StatusMove {

    private Spikes() {
        super("Spikes", 0, Type.GROUND, 32);
    }

    public static final Spikes SPIKES = new Spikes();
}
