package org.shorts.model.moves.floating;

import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FutureSight extends FloatingMove {

    public FutureSight(Pokemon user, int targetIndex) {
        super(
            "Future Sight",
            120,
            100,
            Type.PSYCHIC,
            Category.SPECIAL,
            Range.SINGLE_ADJACENT_ANY,
            16,
            false,
            0,
            user,
            targetIndex,3);
    }

}
