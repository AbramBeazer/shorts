package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class ToxicSpikes extends StatusMove {

    public ToxicSpikes() {
        super("Toxic Spikes", 0, Type.POISON, 32);
    }

    public static final ToxicSpikes TOXIC_SPIKES = new ToxicSpikes();
}
