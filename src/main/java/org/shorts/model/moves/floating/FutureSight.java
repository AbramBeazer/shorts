package org.shorts.model.moves.floating;

import org.shorts.model.moves.Range;
import org.shorts.model.types.Type;

public class FutureSight extends FloatingMove {

    public FutureSight() {
        super(
            "Future Sight",
            120,
            100,
            Type.PSYCHIC,
            Category.SPECIAL,
            Range.NORMAL,
            16,
            false,
            0,
            3);
    }

}
