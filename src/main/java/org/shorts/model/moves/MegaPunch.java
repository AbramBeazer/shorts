package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class MegaPunch extends Move implements PunchingMove {

    public MegaPunch() {
        super("Mega Punch", 80, 85, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 32, true, 0);
    }
}