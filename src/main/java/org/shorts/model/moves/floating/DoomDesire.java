package org.shorts.model.moves.floating;

import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class DoomDesire extends FloatingMove {

    public DoomDesire(Pokemon user, int targetIndex) {
        super(
            "Doom Desire",
            140,
            100,
            Type.STEEL,
            Category.SPECIAL,
            Range.SINGLE_ADJACENT_ANY,
            8,
            false,
            0,
            user,
            targetIndex, 3);
    }
}
