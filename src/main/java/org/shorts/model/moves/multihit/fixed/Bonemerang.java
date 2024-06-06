package org.shorts.model.moves.multihit.fixed;

import org.shorts.model.moves.Range;

import static org.shorts.model.types.Type.GROUND;

public class Bonemerang extends FixedMultiHitMove {

    public Bonemerang() {
        super("Bonemerang", 50, 90, GROUND, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0, 2);
    }
}
