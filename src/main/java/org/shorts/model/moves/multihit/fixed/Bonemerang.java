package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;

import static org.shorts.model.types.Type.GROUND;

public class Bonemerang extends FixedMultiHitMove {

    public Bonemerang() {
        super("Bonemerang", 50, 90, GROUND, Category.PHYSICAL, Range.NORMAL, 16, false, 0, 2);
    }
}
