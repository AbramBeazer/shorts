package org.shorts.model.items.berries;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

import static org.shorts.model.status.VolatileStatusType.MICLE_BERRY_EFFECT;

public class MicleBerry extends Berry {

    private MicleBerry() {
        super("Micle", .25);
    }

    @Override
    public void doEffect(Pokemon user) {
        user.addVolatileStatus(new VolatileStatus(MICLE_BERRY_EFFECT, 1));
    }
}
