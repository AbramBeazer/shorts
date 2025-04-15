package org.shorts.model.moves;

import org.shorts.model.types.Type;

public class KowtowCleave extends Move implements SlicingMove {

    public KowtowCleave() {
        super("KowtowCleave", 85, -1, Type.DARK, Category.PHYSICAL, Range.NORMAL, 16, true, 0);
    }
}
