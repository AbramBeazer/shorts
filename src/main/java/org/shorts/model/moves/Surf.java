package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class Surf extends SpecialMove {
    //TODO: Verify power, PP, etc.
    public Surf() {
        super("Surf", 90, 100, Type.WATER, 24, false, 0);
    }

}
