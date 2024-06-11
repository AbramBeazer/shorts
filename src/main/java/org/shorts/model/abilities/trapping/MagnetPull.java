package org.shorts.model.abilities.trapping;

import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Range;

public class MagnetPull extends Ability {

    private MagnetPull() {
        super("Magnet Pull", Range.ALL_ADJACENT_OPPONENTS);
    }

    public static final MagnetPull MAGNET_PULL = new MagnetPull();

    //TODO: This trapping effect may be something I want to pull out of this ability and put into a check that runs before a Pokémon tries to switch.

}
