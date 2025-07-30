package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class FairyWind extends Move implements WindMove {

    public FairyWind() {
        super("Fairy Wind", 40, 100, Type.FAIRY, Category.SPECIAL, Range.NORMAL, 48, false, 0);
    }
}
