package org.shorts.model.moves.floating;

import org.shorts.model.moves.Range;

import org.shorts.model.types.Type;

public class DoomDesire extends FloatingMove {

    public DoomDesire() {
        super(
            "Doom Desire",
            140,
            100,
            Type.STEEL,
            Category.SPECIAL,
            Range.NORMAL,
            8,
            false,
            0,
            3);
    }
}
